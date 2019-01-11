import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule} from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { AgGridModule } from 'ag-grid-angular';
import { ModalModule } from 'ngx-bootstrap';
import { BsDropdownModule } from 'ngx-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ViewComponentComponent } from './view-component/view-component.component';
import { AddComponentComponent } from './add-component/add-component.component';
import { StakeComponentComponent } from './stake-component/stake-component.component';
import { AddStakeComponentComponent } from './add-stake-component/add-stake-component.component';

@NgModule({
  declarations: [
    AppComponent,
    ViewComponentComponent,
    AddComponentComponent,
    StakeComponentComponent,
    AddStakeComponentComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpModule,
    HttpClientModule,
    AgGridModule.withComponents([]),
    ModalModule.forRoot(),
    BsDropdownModule.forRoot()
  ],
  entryComponents: [AddComponentComponent, AddStakeComponentComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
