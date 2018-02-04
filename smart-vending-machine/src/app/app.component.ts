import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor(private httpClient:HttpClient){

  }

  getProducts(){

   this.httpClient.get('http://35.177.195.110/products?name=%22itu%22')
   .subscribe(
     (data:any) => {
      console.log(data);
     }
   )
  }
}
