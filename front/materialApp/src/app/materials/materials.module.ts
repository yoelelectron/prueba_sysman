import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ByCityPageComponent } from './pages/by-city-page/by-city-page.component';
import { ByTypeDatePageComponent } from './pages/by-date-type-page/by-type-date-page.component';
import { MaterialPageComponent } from './pages/material-page/material-page.component';
import { MaterialsRoutingModule } from './materials-routing.module';
import { SharedModule } from '../shared/shared.module';
import { MaterialTableComponent } from './components/materials-table/material-table.component';
import { FormsModule } from '@angular/forms';




@NgModule({
  declarations: [
    ByCityPageComponent,
    ByTypeDatePageComponent,
    MaterialPageComponent,
    MaterialTableComponent
  ],
  imports: [
    CommonModule,
    MaterialsRoutingModule,
    SharedModule,
    FormsModule
  ]
})
export class MaterialsModule { }
