import { Component, Input } from '@angular/core';
import { Material } from '../../interfaces/material';

@Component({
  selector: 'app-material-table',
  templateUrl: './material-table.component.html',
  styleUrl: './material-table.component.css'
})
export class MaterialTableComponent {

  @Input()
  public materials : Material[] = [];

}
