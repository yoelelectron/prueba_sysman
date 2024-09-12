import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MaterialsService } from './materials.service';
import { Material, Ciudad } from '../interfaces/material';
import { environment } from '../../../environments/environment.development';

describe('MaterialsService', () => {
  let service: MaterialsService;
  let httpMock: HttpTestingController;
  const ciudad: Ciudad = {
    codigo : 1,
    nombre : 'Bogota',
    departamento : {
      codigo : 1,
      nombre : 'Bogota'
    }
  }

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [MaterialsService]
    });

    service = TestBed.inject(MaterialsService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();  // Verifica que no haya peticiones pendientes
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should retrieve all materials (getAll)', () => {
    const dummyMaterials: Material[] = [
      { id: 1, nombre: 'Material 1',descripcion: 'desc',  tipo: 'Type A', precio: 100, fechaCompra: new Date('2023-01-01'), fechaVenta: new Date('2023-12-01'), estado: 'ACTIVO', ciudad: ciudad },
      { id: 2, nombre: 'Material 2', descripcion: 'desc', tipo: 'Type B', precio: 200, fechaCompra: new Date('2023-01-01'), fechaVenta: new Date('2023-12-01'), estado: 'ACTIVO', ciudad: ciudad }
    ];

    service.getAll().subscribe(materials => {
      expect(materials.length).toBe(2);
      expect(materials).toEqual(dummyMaterials);
    });

    const req = httpMock.expectOne(`${environment.baseUrl}/material`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyMaterials);
  });

  it('should search materials by city (searchCity)', () => {
    const dummyMaterials: Material[] = [
      { id: 1, nombre: 'Material 1',descripcion: 'desc',  tipo: 'Type A', precio: 100, fechaCompra: new Date('2023-01-01'), fechaVenta: new Date('2023-12-01'), estado: 'ACTIVO', ciudad: ciudad }
    ];

    service.searchCity('New York').subscribe(materials => {
      expect(materials.length).toBe(1);
      expect(materials).toEqual(dummyMaterials);
    });

    const req = httpMock.expectOne(`${environment.baseUrl}/material/porCiudad/New York`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyMaterials);
  });

  it('should search materials by type and purchase date (searchByTypeAndPurchaseDate)', () => {
    const dummyMaterials: Material[] = [
      { id: 1, nombre: 'Material 1',descripcion: 'desc',  tipo: 'Type A', precio: 100, fechaCompra: new Date('2023-01-01'), fechaVenta: new Date('2023-12-01'), estado: 'ACTIVO', ciudad: ciudad }
    ];

    service.searchByTypeAndPurchaseDate('Type A', '2023-01-01').subscribe(materials => {
      expect(materials.length).toBe(1);
      expect(materials).toEqual(dummyMaterials);
    });

    const req = httpMock.expectOne(`${environment.baseUrl}/material/porTipoYfecha?type=Type A&purchaseDate=2023-01-01`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyMaterials);
  });

  it('should add material (addMaterial)', () => {
    const newMaterial: Material = {
      id: 3, nombre: 'Material 3', descripcion: 'desc', tipo: 'Type C', precio: 300, fechaCompra: new Date('2023-01-01'), fechaVenta: new Date('2023-12-01'), estado: 'ACTIVO', ciudad: ciudad
    };

    service.addMaterial(newMaterial).subscribe(response => {
      expect(response).toEqual(newMaterial);
    });

    const req = httpMock.expectOne(`${environment.baseUrl}/material`);
    expect(req.request.method).toBe('POST');
    req.flush(newMaterial);
  });

  it('should update material (updateMaterial)', () => {
    const updatedMaterial: Material = {
      id: 1, nombre: 'Updated Material', descripcion: 'desc', tipo: 'Type A', precio: 150, fechaCompra: new Date('2023-01-01'), fechaVenta: new Date('2023-12-01'), estado: 'ACTIVO', ciudad: ciudad
    };

    service.updateMaterial(updatedMaterial).subscribe(response => {
      expect(response).toEqual(updatedMaterial);
    });

    const req = httpMock.expectOne(`${environment.baseUrl}/material`);
    expect(req.request.method).toBe('PUT');
    req.flush(updatedMaterial);
  });
});
