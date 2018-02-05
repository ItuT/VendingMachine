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
  cost:any;
  selectedProd:any;

  constructor(private httpClient:HttpClient){
    this.getProducts();
    this.getCoins();
    this.balance = 0.0;
    this.total = 0.0;
    this.change = 0.0;
    this.cost = 0.0;
  }


  getCoins(){
    //http://35.177.195.110/products?name=%22itu%22
    //http://localhost:8080/VendingMachineApp/coins
    this.httpClient.get('http://35.177.195.110/coins')
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
  // http://localhost:8080/VendingMachineApp/products?name=%22itu%22
   this.httpClient.get('http://35.177.195.110/products')
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

  postProduct(){
    this.httpClient.post('http://localhost:8080/VendingMachineApp/products',{
      "product":this.selectedProd,
      "paid":this.total
    })
    .subscribe(
      (data:any) => {
        console.log(data);
      }
    )
  }

  getProductPrice(productName:any){
    return "R "+window.localStorage.getItem(productName)+"0";
  }

  coinInserted(coin:any){
    
      this.total = this.total + parseFloat(window.localStorage.getItem(coin));
      //console.log(window.localStorage.getItem(coin));
  }

  getBalance(){
    if(this.cost > this.total)
    this.balance = this.cost - this.total;
    else
      this.balance = 0;
    return this.balance;
  }

  productSelected(prod: any){
    this.selectedProd = ""+prod;
    this.cost = parseFloat(window.localStorage.getItem(prod));
  }

  getTotalPaid(){
    return this.total;
  }

  getChange(){
    if(this.total >= this.cost){
      this.change = this.total - this.cost;
     // this.postProduct();
    }
    return this.change;
  }

}
