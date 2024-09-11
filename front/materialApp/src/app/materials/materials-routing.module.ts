import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ByCityPageComponent } from './pages/by-city-page/by-city-page.component';
import { ByTypeDatePageComponent } from './pages/by-date-type-page/by-type-date-page.component';
import { MaterialPageComponent } from './pages/material-page/material-page.component';
import { MaterialTableComponent } from './components/materials-table/material-table.component';

const routes: Routes = [
  {
    path: 'by-city',
    component: ByCityPageComponent
  },
  {
    path: 'by-type-and-purchasedate',
    component: ByTypeDatePageComponent
  },
  {
    path: 'home',
    component: MaterialPageComponent
  },
  {
    path: '**',
    redirectTo: 'home'
  },
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports : [
    RouterModule
  ]
})
export class MaterialsRoutingModule { }
