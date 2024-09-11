export interface Material {
  id:          number;
  nombre:      string;
  descripcion: string;
  tipo:        string;
  precio:      number;
  fechaCompra: Date;
  fechaVenta:  Date;
  estado:      string;
  ciudad:      Ciudad;
}

export interface Ciudad {
  codigo:       number;
  nombre:       string;
  departamento: Departamento;
}

export interface Departamento {
  codigo: number;
  nombre: string;
}
