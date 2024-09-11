import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MaterialsService } from '../../services/materials.service';
import { switchMap } from 'rxjs';
import { Material } from '../../interfaces/material';

@Component({
  selector: 'app-material-page',
  templateUrl: './material-page.component.html',
  styleUrl: './material-page.component.css'
})
export class MaterialPageComponent implements OnInit {

  public materials?: Material[];

  constructor( private activatedRoute: ActivatedRoute,
    private router : Router,
    readonly materialService: MaterialsService,
  ){}

  ngOnInit(): void {
    this.materialService.getAll()
      .subscribe(r => this.materials = r)
  //   this.activatedRoute.params
  //     .pipe(
  //       switchMap( ({ id }) => this.materialService.searchMaterials()))
  //     )
  //     .subscribe(( res ) => {
  //       if(!res){
  //         return this.router.navigateByUrl('');
  //       }
  //       return this.country = res;
  //     });
  }
}
