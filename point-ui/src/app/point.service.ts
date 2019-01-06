import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Http, Headers, RequestOptions } from "@angular/http";
import { map } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class PointService {

  constructor(private aHttp : Http, private http : HttpClient) { }

  private userUrl = 'http://localhost:8080/api/v1/points';
  private delUrl = 'http://localhost:8080/api/v1/points?id=';

  private headers = new Headers({ 'Content-Type': 'application/json'});     
  private options = new RequestOptions({headers: this.headers});

  public getPoints() {
    return this.aHttp.get(this.userUrl, this.options).pipe(map(response => {
        {
          return response.json(); 
        };
    }));
  }

   public deleteUser(id) {
    return this.aHttp.delete(this.delUrl+id,this.options).pipe(map(response => {
        {
          return response.json(); 
        };
    }));
  }

  public addPoint(data) {
    return this.aHttp.post(this.userUrl,data,this.options).pipe(map(response => {
        {
          return response.json(); 
        };
    }));

  }

  public updatePoint(data) {
    return this.aHttp.put(this.userUrl,data,this.options).pipe(map(response => {
        {
          return response.json(); 
        };
    }));

  }
  
}
