import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, of } from 'rxjs';
import { Ciudad } from '../interfaces/material';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class CitiesService {

  constructor( private http: HttpClient) { }

  getAllCities():Observable<Ciudad[]>{
    const getAll: string = `${environment.baseUrl}/ciudades`;
    return this.apiQueryGET(getAll);
  }

  apiQueryGET(query:string):Observable<Ciudad[]>{
    return this.http.get<Ciudad[]>( query )
    .pipe(
      catchError(() => of([]))
    );
  };

}
