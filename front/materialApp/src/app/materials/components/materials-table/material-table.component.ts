import { Component, Input } from '@angular/core';
import { Material } from '../../interfaces/material';
import { MaterialsService } from '../../services/materials.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-material-table',
  templateUrl: './material-table.component.html',
  styleUrl: './material-table.component.css'
})
export class MaterialTableComponent {

  @Input()
  public materials : Material[] = [];

  constructor(readonly _materialService : MaterialsService, private router: Router){
  }

  selectMaterial(data: Material){
    this._materialService.setMaterial(data);
    this.router.navigate(['/materials/edit-material']).then(s => {if (s) {console.log('ok');
    } else { console.log('nada');
    }});
  }

  createNewMaterial() {
    this._materialService.setMaterial(null);
    this.router.navigate(['/materials/create-material']).then(s => {if (s) {console.log('ok');
    } else { console.log('nada');
    }});
  }
}
