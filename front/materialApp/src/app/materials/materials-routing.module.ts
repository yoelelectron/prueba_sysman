import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ByCityPageComponent } from './pages/by-city-page/by-city-page.component';
import { ByTypeDatePageComponent } from './pages/by-date-type-page/by-type-date-page.component';
import { MaterialPageComponent } from './pages/material-page/material-page.component';
import { MaterialFormComponent } from './components/material-form/material-form.component';

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
    path: 'edit-material',
    component: MaterialFormComponent
  },
  {
    path: 'create-material',
    component: MaterialFormComponent
  },

  {
    path: 'home',
    component: MaterialPageComponent
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
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
