import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'The Smartest Vending Machine The World Has Ever Seen!';
  items:any[];

  constructor(private httpClient:HttpClient){
    this.getProducts();
  }

  getProducts(){
   
   this.httpClient.get('http://35.177.195.110/products?name=%22itu%22')
   .subscribe(
     (data:any) => {
       this.items = [];
       let json = data["products"][0];
       console.log(json);
       for(var key in json){
        console.log(json[key]);
        this.items.push(key);
       }
      
     }
   )
  }
}
