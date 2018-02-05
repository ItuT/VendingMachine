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
  coins:any[];
  total:any;
  balance:any;
  change:any;

  constructor(private httpClient:HttpClient){
    this.getProducts();
    this.getCoins();
  }


  getCoins(){
    //http://35.177.195.110/products?name=%22itu%22
    this.httpClient.get('http://localhost:8080/VendingMachineApp/coins')
    .subscribe(
      (data:any) => {
        this.coins = [];
        let json = data["coins"][0];
        console.log(json);
        for(var key in json){
         window.localStorage.setItem(key,json[key]);
         console.log(json[key]);
         this.coins.push(key);
        }
       
      }
    )
   }

  getProducts(){
   //http://35.177.195.110/products?name=%22itu%22
   this.httpClient.get('http://localhost:8080/VendingMachineApp/products?name=%22itu%22')
   .subscribe(
     (data:any) => {
       this.items = [];
       let json = data["products"][0];
       console.log(json);
       for(var key in json){
        window.localStorage.setItem(key,json[key]);
        console.log(json[key]);
        this.items.push(key);
       }
      
     }
   )
  }

  getProductPrice(productName:any){
    return "R "+window.localStorage.getItem(productName)+"0";
  }
}
