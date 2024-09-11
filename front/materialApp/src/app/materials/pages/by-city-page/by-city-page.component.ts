import { Component } from '@angular/core';
import { MaterialsService } from '../../services/materials.service';
import { Material } from '../../interfaces/material';

@Component({
  selector: 'app-by-city-page',
  templateUrl: './by-city-page.component.html',
  styleUrl: './by-city-page.component.css'
})
export class ByCityPageComponent {

  public materials:Material[] = [];

  constructor(readonly _materialService : MaterialsService){

  }

  searchByCity(term:string):void{
    this._materialService.searchCity(term)
      .subscribe( (materials: Material[]) => {
        this.materials = materials
      });
  }

}
