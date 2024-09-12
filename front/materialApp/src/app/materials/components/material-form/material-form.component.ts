import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CitiesService } from '../../services/cities.service';
import { Ciudad, Material } from '../../interfaces/material';
import { ActivatedRoute, Router } from '@angular/router';
import { MaterialsService } from '../../services/materials.service';

@Component({
  selector: 'app-material-form',
  templateUrl: './material-form.component.html',
  styleUrl: './material-form.component.css'
})
export class MaterialFormComponent implements OnInit {

  materialForm!: FormGroup;
  material: Material | null = null;

  // Opciones para los selects
  estados: string[] = ['ACTIVO', 'DISPONIBLE', 'ASIGNADO'];
  ciudades: Ciudad[] = [];

  constructor(private fb: FormBuilder,
    readonly _citiesService: CitiesService,
    private router: Router,
    readonly _materialService: MaterialsService
  ) {

  }

  ngOnInit(): void {
    console.log('Se carga');


    this.initForm();

    this._materialService.getMaterial().subscribe((material) => {
      this.material = material;
      if(this.material){
        this.materialForm.patchValue(this.material)
      }
    });

    this._citiesService.getAllCities().subscribe(c => this.ciudades = c);

    console.log(this.material);

  }

  initForm(){
    this.materialForm = this.fb.group({
      id: [''],
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      tipo: ['', Validators.required],
      precio: [null, [Validators.required, Validators.min(0)]],
      fechaCompra: ['', Validators.required],
      fechaVenta: ['', Validators.required],
      estado: ['', Validators.required],
      ciudad: ['', Validators.required],
    });
  }

  cosa(){
    this.router.navigate(['/materials/home']);
  }

  save(): void {
    console.log(this.materialForm.get('ciudad')?.value);

    if(this.materialForm.valid){
      if(this.material){
        console.log('Logica de editar');
        console.log(this.materialForm.value);
        this._materialService.updateMaterial(this.materialForm.value)
          .subscribe( res => {
            console.log("Respuesta del servidor", res);

          },
        err => {
          console.error("Error actualizando", err)
        })


      }else {
        console.log('Logica de Crear');
        console.log(this.materialForm);
        this._materialService.addMaterial(this.materialForm.value)
          .subscribe(s => {
            console.log(s);
          },
          err => {
            console.error('Se ha presnetado un error', err)
          }
        )

      }

      this.router.navigate(['/materials/home'])
    }
  }
}
