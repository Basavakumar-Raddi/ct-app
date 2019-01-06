import { Component, OnInit } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import {Point} from '../point';
import {PointService } from '../point.service';

@Component({
  selector: 'app-add-component',
  templateUrl: './add-component.component.html',
  styleUrls: ['./add-component.component.css']
})
export class AddComponentComponent implements OnInit {


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

  locations = ['EE', 'FI', 'LV'];

  model = new Point('','','',null);

  constructor(private pointService : PointService, public bsModalRef: BsModalRef) { }

  ngOnInit() {
    if(this.selRow != undefined) {
      this.model = new Point(this.selRow[0]['id'],this.selRow[0]['measurementDay'],this.selRow[0]['location'],this.selRow[0]['value']);
    }
    console.log(this.selRow);
  }


  onClickSubmit(data: Point) {
      this.bsModalRef.hide();
      if(this.selRow != undefined) {
          data.id = this.selRow[0]['id'];
          this.pointService.updatePoint(data).subscribe(response => {
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
          this.pointService.addPoint(data).subscribe(response => {
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
