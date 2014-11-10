
/*
<<copyright 2013 Pablo Sánchez Robles>>


This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.
*/

package divide_cadena;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Este programa sirve de ayuda para el desencriptado de textos polialfabéticos,
 * podemos descubrir trigramas repetidos, y calculando los divisores de las distancias
 * probar estos pasándoselos al programa  en la variable NUMERO_DE_FUNCIONES.
 *
 * En los distintos métodos que componen el programa viene un comentario con una breve descripción
 * de los mismos.
 */


public class DesencriptadoPolialfabetico {
    /**
     * @param args the command line arguments
     */

    //Número de funciones, cambiar para cada posible divisor de las distancias entre trigramas.
        static int NUMERO_DE_FUNCIONES = 12;

    //Array con las distintas funciones, el número de funciones es la variable anterior.
        static ArrayList<Funcion> funciones = new ArrayList<Funcion>();

    //abecedario del lenguaje del texto sin encriptar, en este caso el inglés
        static char abecedario[] = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l',
                                        'm','n','o','p','q','r','s','t','u','v','w',
                                        'x','y','z'};


     //Los dos textos mostrados a continuación son la carta que Edgar Allan Poe nunca pudo desencriptar.
        //Texto encriptado con espacios y símbolos especiales.
        static String textoCompleto="GE JEASGDXV, ZIJ GL MW, LAAM, XZY ZMLWHFZEK EJLVDXW KWKE TX LBR ATGH LBMX AANU BAI VSMUKKSS PWNVLWK AGH GNUMK WDLNZWEG JNBXVV OAEG ENWB ZWMGY MO MLW WNBX MW AL PNFDCFPKH WZKEX HSSF XKIYAHUL. MK NUM YEXDM WBXY SBC HV WYX PHWKGNAMCUK ?.";
        //Texto encriptado sin espacios y sin símbolos especiales.
        static String texto = "GEJEASGDXVZIJGLMWLAAMXZYZMLWHFZEKEJLVDXWKWKETXLBRATGHLBMXAANUBAIVSMUKKSSPWNVLWKAGHGNUMKWDLNZWEGJNBXVVOAEGENWBZWMGYMOMLWWNBXMWALPNFDCFPKHWZKEXHSSFXKIYAHULMKNUMYEXDMWBXYSBCHVWYXPHWKGNAMCUK";

        //Variable donde se almacenará el texto desencriptado.
        static String textoDesencriptado="";

        //Array de distancias entre trigramas.
        static int distanciasIguales[] = new int [10];


        //Separa las letras del texto en las distintas funciones
        static public void separaLetras()
        {
            int contador = 0;
            for(int i =0; i< texto.length();i++)
            {
                if(contador == NUMERO_DE_FUNCIONES )
                {
                    contador = 0;
                }
                funciones.get(contador).funcion = funciones.get(contador).funcion + texto.substring(i, i+1);
                contador++;
            }     
        }


        //Calcula las frecuencias de las letras de la función f y los almacena en f.frec[]
        static public void calculaFrecuencias(Funcion f)
        {
        
            f.frec = new int[f.funcion.length()];
            f.frec[0] = 1;
            f.alfabeto = f.funcion.substring(0, 1);
            for(int i = 1; i < f.funcion.length();i++)
            {
                for(int j = 0; j < f.alfabeto.length();j++)
                {
                    if(f.funcion.substring(i, i+1).compareTo(f.alfabeto.substring(j, j+1)) ==0)
                    {
                        f.frec[j]++;
                        j = f.alfabeto.length();
                        f.contada= true;
                    }
                }
                if(!f.contada)
                {
                    f.alfabeto=f.alfabeto+f.funcion.substring(i,i+1);
                    f.frec[f.pos] = 1;
                    f.pos++;
                }else
                {
                    f.contada = false;
                }
            }
            
        }


        //Ordena el alfabeto de la función f por frecuencias de mayor a menor
        public static void ordenaAlfabetoPorFrecuencias(Funcion f)
        {
            String cadenaAux = f.alfabeto;
            int frecuenciaAux;
            for(int i = 0; i< f.alfabeto.length(); i++)
            {
                for(int j = 0; j < f.alfabeto.length()-1; j++)
                {
                    if(f.frec[j] < f.frec[j+1])
                    {
                        frecuenciaAux = f.frec[j];
                        f.frec[j] = f.frec[j+1];
                        f.frec[j+1] = frecuenciaAux;
                        cadenaAux = cadenaAux.substring(0, j) +cadenaAux.substring(j+1, j+2) + cadenaAux.substring(j, j+1)+ cadenaAux.substring(j+2, cadenaAux.length());
                    }
                }
            }
            f.alfabeto = cadenaAux;
        }


        //Muestra las frecuencias de las letras que hay en la función f
        public static void mostrarFrecuencias(Funcion f)
        {
            for(int i = 0; i < f.alfabeto.length();i++)
            {
                System.out.print(f.alfabeto.substring(i, i+1)+" = ");
                System.out.println(f.frec[i]);
            }
            System.out.println("----");
        }

       //Prueba los caracteres que se le introducen  (los mas comunes en cada lenguaje) en la función f

        public static void pruebaCaracteresComunes(Funcion f, String primera, String segunda, String tercera, String cuarta,String quinta, String sexta)
        {
            for(int i = 0; i<f.funcion.length();i++)
            {
                if(f.funcion.substring(i, i+1).compareTo(f.alfabeto.substring(1, 2))==0)
                {
                    f.desencriptada = f.desencriptada+primera;
                }else if(f.funcion.substring(i, i+1).compareTo(f.alfabeto.substring(2, 3))==0)
                {
                    f.desencriptada = f.desencriptada+segunda;
                }else if(f.funcion.substring(i, i+1).compareTo(f.alfabeto.substring(3, 4))==0)
                {
                    f.desencriptada = f.desencriptada+tercera;
                }else if(f.funcion.substring(i, i+1).compareTo(f.alfabeto.substring(4, 5))==0)
                {
                    f.desencriptada = f.desencriptada+cuarta;
                }else if(f.funcion.substring(i, i+1).compareTo(f.alfabeto.substring(5, 6))==0)
                {
                    f.desencriptada = f.desencriptada+quinta;
                }else if(f.funcion.substring(i, i+1).compareTo(f.alfabeto.substring(6, 7))==0)
                {
                    f.desencriptada = f.desencriptada+sexta;
                }

                else
                {
                    f.desencriptada = f.desencriptada+f.funcion.substring(i, i+1);
                }
            }
            
        }

        //Une los desencriptados de las distintas funciones
        public static void uneDesencriptados()
        {
            for(int i = 0; i < funciones.get(0).funcion.length();i++)
            {
                for(int j = 0; j<NUMERO_DE_FUNCIONES;j++)
                {
           
                    if(funciones.get(j).funcion.length() > i)
                    {

                        textoDesencriptado = textoDesencriptado+funciones.get(j).desencriptada.substring(i, i+1);
                    }
                }
            }
            textoDesencriptado =textoDesencriptado+".";
        }


        //Incia el array de funciones con el número de funciones NUMERO_DE_FUNCIONES
        public static void inicializaArrayList()
        {
            for(int i = 0; i < NUMERO_DE_FUNCIONES;i++)
            {
                funciones.add(new Funcion());
            }
        }


        //Inserta los espacios y los símbolos especiales una vez el texto se ha desncriptado y se han unido las distintas funciones
        public static void introduceEspacios()
        {
            String aux="";
            int cont = 0;
            for(int i = 0; i < textoCompleto.length();i++)
            {
                if((textoCompleto.substring(i, i+1).compareTo(" ")==0)
                        ||(textoCompleto.substring(i, i+1).compareTo(",")==0)
                        ||(textoCompleto.substring(i, i+1).compareTo(".")==0)
                        ||(textoCompleto.substring(i, i+1).compareTo("?")==0))
                {
                    aux = aux+textoCompleto.substring(i, i+1);
                }
                else
                {
                    if(cont<textoDesencriptado.length()-1)
                    {
                        aux = aux+textoDesencriptado.substring(cont, cont+1);
                        cont++;
                    }
                    
                }
                
            }
            textoDesencriptado = aux;
            
        }
///Busca en este caso trigramas repetidos en el texto.
        public static void buscaCadenasRepetidas()
        {
            String aux;int pos = 0;
            for(int i = 0; i < texto.length(); i++)
            {
                aux = texto.substring(i, i+3);
                for(int j = 1; j < texto.length();j++)
                {
                    if(aux.compareTo(texto.substring(j,j+3)) == 0)
                    {
                        if(i != j)
                        {
                            distanciasIguales[pos] = j - i;
                            System.out.println(aux+" "+texto.substring(j,j+3)+" = "+  distanciasIguales[pos]);
                            pos++;
                        }
                    }
                }
            }
        }


//Almacena en un fichero de texto el texto desencriptado
        public static void guardarEnfichero()
        {
            FileWriter fichero = null;

            PrintWriter pw = null;


            try
            {
                fichero = new FileWriter("./salida.txt");
                pw = new PrintWriter(fichero);
                pw.println(textoDesencriptado);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
               try {


               if (null != fichero)
                  fichero.close();
               } catch (Exception e2) {
                  e2.printStackTrace();
               }
            }
        }

    //Desencripta la función f probando la distancia
        public static void pruebaDesencriptado(Funcion f, int distancia)
        {
            int trans = 0;
            String car;
            f.desencriptada = "";
            for(int i = 0; i < f.funcion.length(); i++)
            {
                for(int j = 0; j< abecedario.length;j++)
                {
                    car = ""+abecedario[j];
                    car = car.toUpperCase();
                    
                    if(f.funcion.substring(i, i+1).compareTo(car)==0)
                    {
                        trans = (j+distancia)%26;
                        if(trans<0){trans = trans+26;}
                        System.out.println(trans);
                        f.desencriptada = f.desencriptada + abecedario[trans];
                    }
                }

            }
            
        }

        public static void main(String[] args) {

            
                inicializaArrayList();
                separaLetras();

                for(int i = 0; i < NUMERO_DE_FUNCIONES;i++)
                {
                    calculaFrecuencias(funciones.get(i));
                    ordenaAlfabetoPorFrecuencias(funciones.get(i));
                   // mostrarFrecuencias(funciones.get(i));
                   // pruebaCaracteresComunes(funciones.get(i),"t","a","e","o","i","n");
                    funciones.get(i).desencriptada = funciones.get(i).funcion;
                }
                pruebaDesencriptado(funciones.get(0),13);
                pruebaDesencriptado(funciones.get(1),18);
                pruebaDesencriptado(funciones.get(2),7);
                pruebaDesencriptado(funciones.get(3),22);
                pruebaDesencriptado(funciones.get(4),23);
                pruebaDesencriptado(funciones.get(5),8);
                pruebaDesencriptado(funciones.get(6),7);
                pruebaDesencriptado(funciones.get(7),0);
                pruebaDesencriptado(funciones.get(8),7);
                pruebaDesencriptado(funciones.get(9),22);
                pruebaDesencriptado(funciones.get(10),8);
                pruebaDesencriptado(funciones.get(11),6);
              

                uneDesencriptados();
                introduceEspacios();
                guardarEnfichero();
              
               System.out.println(textoDesencriptado);
        }
}
