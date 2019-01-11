import { Component, OnInit } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import {Stake} from '../stake';
import {StakeService } from '../stake.service';


@Component({
  selector: 'app-add-stake-component',
  templateUrl: './add-stake-component.component.html',
  styleUrls: ['./add-stake-component.component.css']
})
export class AddStakeComponentComponent implements OnInit {

  title: string;
  selRow: any;
  closeBtnName: string;
  message: string;
  errorMsg: string = '';
  successMsg: string = '';
  errorFlag: boolean = false;
  successFlag: boolean = false;
  showSuccessMsg: boolean = true;
  response: Object = null;

  model = new Stake('','','','',null,null);

  constructor(private stakeService : StakeService, public bsModalRef: BsModalRef) { }

  ngOnInit() {
    if(this.selRow != undefined) {
        this.model = new Stake(this.selRow[0]['id'],
        this.selRow[0]['name'],      
        this.selRow[0]['sector'],
        this.selRow[0]['initiationDate'],
        this.selRow[0]['investmentRequired'],
        this.selRow[0]['stakeOffered']);
        
    }
    console.log(this.selRow);
  }

  onClickSubmit(data: Stake) {
    this.bsModalRef.hide();
    if(this.selRow != undefined) {
        data.id = this.selRow[0]['id'];
        this.stakeService.updatePoint(data).subscribe(response => {
        if (!response.success) {
          this.errorFlag = true;
          this.errorMsg = response.message;
          alert(this.errorMsg);
        } else {
          this.successFlag = true;
          this.showSuccessMsg = false;
          this.successMsg = response.message;
          alert(this.successMsg );
        }
      }, error => {
        this.errorFlag = true;
        this.errorMsg = "add failed";
        alert(this.errorMsg);
      });
    } else {
        this.stakeService.addPoint(data).subscribe(response => {
          if (!response.success) {
            this.errorFlag = true;
            this.errorMsg = response.message;
            alert(this.errorMsg);
          } else {
            this.successFlag = true;
            this.showSuccessMsg = false;
            this.successMsg = response.message;
            //this.updateGridData(id[0]['id']);
            alert(this.successMsg );
          }
        }, error => {
          this.errorFlag = true;
          this.errorMsg = "add failed";
          alert(this.errorMsg);
        });
    }  
  }

}
