import { Component, OnInit,  AfterViewInit, TemplateRef } from '@angular/core';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import { GridOptions } from 'ag-grid-community';
import {StakeService } from '../stake.service';
import {AddStakeComponentComponent} from '../add-stake-component/add-stake-component.component';
import * as _ from 'lodash';

@Component({
  selector: 'app-stake-component',
  templateUrl: './stake-component.component.html',
  styleUrls: ['./stake-component.component.css']
})
export class StakeComponentComponent implements OnInit, AfterViewInit {
  gridOptions: GridOptions;
  columnDef = [];
  rowData = [];
  errorMsg: string = '';
  successMsg: string = '';
  errorFlag: boolean = false;
  successFlag: boolean = false;
  showSuccessMsg: boolean = true;
  response: Object = null;
  minValue: number = 0;
  averageValue: number = 0;
  maxValue: number = 0;
  totalValue: number = 0;
  rowSelected: boolean = false;
  modalRef: BsModalRef;
  message: string;

  constructor(private stakeService : StakeService, private modalService: BsModalService) { 
    this.gridOptions = {
      columnDefs: this.columnDef,
      rowData: this.rowData,
      rowHeight: 32,
      headerHeight: 38,
      enableSorting: true,
      enableFilter: true,
      rowSelection: 'single',
      suppressAnimationFrame: true,
      suppressMenuHide: true,
      onSelectionChanged: this.onSelectionChanged.bind(this)
    };
  }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    this.columnDef = [
      {headerName: 'Name', field: 'name', suppressFilter: true},
      {headerName: 'Sector', field: 'sector', filter: "agTextColumnFilter"},
      {headerName: 'InitiationDate', field: 'initiationDate', suppressFilter: true},
      {headerName: 'InvestmentRequired', field: 'investmentRequired', suppressFilter: true},
      {headerName: 'StakeOffered', field: 'stakeOffered', suppressFilter: true, valueFormatter: this.numberFormatter}
    ];

  this.stakeService.getPoints().subscribe(response => {
      if (!response.success) {
        this.errorFlag = true;
        this.errorMsg = response.message;
      } else {
        this.response = response.response;
        this.rowData = [];
        for (var columnEntry in response.response) {
          this.rowData.push(response.response[columnEntry]);
        }
        this.successFlag = true;
        this.showSuccessMsg = false;
        this.successMsg = response.message;
        this.gridOptions.api.setColumnDefs(this.columnDef);
        this.gridOptions.api.setRowData(this.rowData);
        this.gridOptions.api.sizeColumnsToFit();
      }
    }, error => {
      this.errorFlag = true;
      this.errorMsg = "Data Fetch failed";
    });
  }

  deletePoint() {
    var id=this.gridOptions.api.getSelectedRows();
    console.log(id);
    if(id){
      this.stakeService.deleteUser(id[0]['id']).subscribe(response => {
        if (!response.success) {
          this.errorFlag = true;
          this.errorMsg = response.message;
          alert(this.errorMsg);
        } else {
          this.successFlag = true;
          this.showSuccessMsg = false;
          this.successMsg = response.message;
          this.updateGridData(id[0]['id']);
          alert(this.successMsg );
        }
      }, error => {
        this.errorFlag = true;
        this.errorMsg = "delete failed";
      });
    }
  }

  updateGridData(id){
    _.remove(this.rowData, {id: id});
    this.gridOptions.api.setRowData(this.rowData);
    this.gridOptions.api.sizeColumnsToFit();
  }

  onSelectionChanged(){
    this.rowSelected = true
  }

  numberFormatter(params) {
    return params.value + '%';
}

  addPoint() {
    const initialState = {
      title: 'Add Point'
    };
    this.modalRef = this.modalService.show(AddStakeComponentComponent, {initialState});
    this.modalRef.content.closeBtnName = 'Close';
  }

  updatePoint() {
    var selRow=this.gridOptions.api.getSelectedRows();
    const initialState = {
      selRow: selRow,
      title: 'Update Point'
    };
    this.modalRef = this.modalService.show(AddStakeComponentComponent, {initialState});
    this.modalRef.content.closeBtnName = 'Close';
  }


}
