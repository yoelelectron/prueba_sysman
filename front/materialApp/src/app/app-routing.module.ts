import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [

  {
    path: 'materials',
    loadChildren: () => import('./materials/materials.module')
      .then(m => m.MaterialsModule)
  },
  {
    path: '**',
    redirectTo : 'materials'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
