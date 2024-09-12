import { Component } from '@angular/core';
import { MaterialsService } from '../../services/materials.service';
import { Material } from '../../interfaces/material';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-by-type-date-page',
  templateUrl: './by-type-date-page.component.html',
  styleUrl: './by-type-date-page.component.css'
})
export class ByTypeDatePageComponent {

  public materials:Material[] = [];
  public purchaseDate:any;

  constructor(readonly _materialServices : MaterialsService, private datePipe : DatePipe){

  }

  searchbyDateAndType(term:string):void{
    const date = String(this.purchaseDate).substring(0,10);

    this._materialServices.searchByTypeAndPurchaseDate(term,date)
      .subscribe( (materials: Material[]) => {
        this.materials = materials
      });
  }

}
