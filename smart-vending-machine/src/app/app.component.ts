import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Smart Vending Machine';
  items:any[];
  coins:any[];
  total:any;
  balance:any;
  change:any;
  cost:any;
  selectedProd:any;
  state:any;
  serverResult: boolean = false;
  serverRes:any;
  coinsReturned: boolean = false;
  coinsToReturn:any;
  imageUrl = "assets/images/ajax-loader.gif";
  loading = false;
  buyingRequest = false;
  coinsReloading = false;

  constructor(private httpClient:HttpClient){
    this.getProducts();
    this.getCoins();
    this.state = "START";
    this.balance = 0.0;
    this.total = 0.0;
    this.change = 0.0;
    this.cost = 0.0;
  
  }

  getState(state: any){
    return this.state;
  }

  setState(state: any){
    this.state = state;
  }

  getCoins(){
    this.coinsReloading = true;
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
          this.coinsReloading = false;
        }
       
      }
    )
   }

  getProducts(){
    this.loading = true;
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
        this.loading = false;
       }
      
     }
   )
  }

  postProduct(){
    console.log("cost = "+this.cost);
    if(this.cost > 0.0 && this.getTotalPaid() > 0.0){
      console.log("post()");
    this.buyingRequest = true;
    console.log("price = "+this.total);
    //
    //http://localhost:8080/VendingMachineApp/products
    this.httpClient.post('http://35.177.195.110/products',{
      "product":this.selectedProd,
      "paid":""+this.total,
      "state":"start"
    })
    .subscribe(
      (data:any) => {
        this.serverResult = true;
        console.log(data);
        this.serverRes = ""+data["result"];
        this.buyingRequest = false;
        if(this.serverRes.indexOf("joy") !== -1){
         // this.refreshCoins();
          this.refreshProducts();
          this.balance = 0.0;
          this.cost = 0.0;
          this.change = 0.0;
          this.total = 0.0;

        }
      }
    )
  }else{
    this.coinsReturned = false;
    this.serverResult = true;
    console.log("paid "+this.getTotalPaid());
    if(this.getTotalPaid() > 0.0)
      this.serverRes = "Please select Product";
    else{
      if(this.cost <= 0)
      this.serverRes = "Please select Product and Insert Coins!";
      else
      this.serverRes = "Insert Coins";
    }
  }
  }

  getProductPrice(productName:any){
   // this.serverRes = false;
    //this.coinsReturned =false;
    return "R "+window.localStorage.getItem(productName)+"0";
  }

  coinInserted(coin:any){
    this.serverResult = false;
    this.coinsReturned = false;
      this.total = this.total + parseFloat(window.localStorage.getItem(coin));
      this.coinsToReturn = this.total;
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
    this.serverResult = false;
    this.coinsReturned =false;
    console.log("prooo "+prod);
    this.selectedProd = prod;
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

  refreshProducts(){
    this.items = null;
    this.selectedProd = undefined;

   // this.serverResult = false;
   // this.coinsReturned =false;
    this.cost = 0.0;
    this.getBalance();
    this.change = this.balance;
    this.getProducts();
  }

  refreshCoins(){
    this.coins = null;
    this.serverResult = false;
    this.coinsReturned =false;
    this.getCoins();
  }

  returnCoins(){
    console.log("ret "+this.getTotalPaid());
    if(this.getTotalPaid() > 0.0){
      this.balance = 0.0;
      this.change = 0.0;
      this.cost = 0.0;
      this.total = 0.0;
      this.coinsReturned = true;
      this.serverResult = false;
      if(this.selectedProd)this.refreshProducts();
      this.getCoins();
    }else{
      this.coinsReturned = false;
      this.serverResult = true;
      this.serverRes = "No Coins Inserted!";
    }
  }

  getCoinsToReturn(){
    return this.coinsToReturn;
  }

}
