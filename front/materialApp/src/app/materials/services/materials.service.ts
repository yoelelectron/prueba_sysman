import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map, Observable, of } from 'rxjs';
import { Material } from '../interfaces/material';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class MaterialsService {

  private selectedMaterial = new BehaviorSubject<Material | null>(null);

  constructor(private http: HttpClient) { }


  searchCity(query: string): Observable<Material[]> {
    const getAllByCity: string = `${environment.baseUrl}/material/porCiudad/${query}`;
    return this.apiQueryGET(getAllByCity);
  }

  getAll(): Observable<Material[]> {
    const getAll: string = `${environment.baseUrl}/material`;
    return this.apiQueryGET(getAll);
  }

  searchByTypeAndPurchaseDate(query: string, date: string): Observable<Material[]> {
    const getAllByTypeAndDate: string = `${environment.baseUrl}/material/porTipoYfecha?type=${query}&purchaseDate=${date}`;
    return this.apiQueryGET(getAllByTypeAndDate);
  }

  apiQueryGET(query: string): Observable<Material[]> {
    return this.http.get<Material[]>(query)
      .pipe(
        catchError(() => of([]))
      );
  };

  addMaterial(material : Material):Observable<any>{
    console.log(material)
    return this.http.post(`${environment.baseUrl}/material`,material);
  }

  updateMaterial(material: Material): Observable<any>{
    console.log(material)
    return this.http.put<any>(`${environment.baseUrl}/material`,material);
  }

  getMaterial(): Observable<Material | null> {
    return this.selectedMaterial.asObservable();
  }

  setMaterial(material : Material | null) {
    this.selectedMaterial.next(material)
  }
}
