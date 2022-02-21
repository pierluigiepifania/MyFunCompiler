#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>

char* convert (int n) {
    char* to_ret = (char *) malloc (101);
    sprintf(to_ret,"%d",n);
    return to_ret;
}
char* convertF (float n) {
    char* to_ret = (char *) malloc (101);
    sprintf(to_ret,"%f",n);
    return to_ret;
}
char* convertS (char * s) {
    char* to_ret = (char *) malloc (101);
    sprintf(to_ret,"%s",s);
    return to_ret;
}
int power(int a, int b) {
int i = 0;
int res =1;
while(i<b) {
res = res*a;
i = i+1;
}
return res;
 }

int  print_menu () {
int choose;
printf  ("Scegli l'operazione da svolgere per continuare") ;
printf  ("\t(1) Somma di due numeri") ;
printf  ("\t(2) Moltiplicazione di due numeri") ;
printf  ("\t(3) Divisione intera fra due numeri positivi") ;
printf  ("\t(4) Elevamento a potenza") ;
printf  ("\t(5) Successione di Fibonacci (ricorsiva)") ;
printf  ("\t(6) Successione di Fibonacci (iterativa)") ;
printf  ("\t(0) Esci") ;
printf ("--> ") ;scanf (" %d",&choose) ;
return choose;
}
void do_sum () {
float op1,op2;
printf  ("\n(1) SOMMA\n") ;
printf ("Inserisci il primo operando: ") ;scanf (" %f",&op1) ;
printf ("Inserisci il secondo operando: ") ;scanf (" %f",&op2) ;
printf  ("") ;
printf  ("La somma tra op1 %f e op2 %f vale op1+op2 %f",op1,op2,op1+op2) ;
}
void do_mul () {
float op1,op2;
printf  ("\n(2) MOLTIPLICAZIONE") ;
printf ("\nInserisci il primo operando: ") ;scanf (" %f",&op1) ;
printf ("Inserisci il secondo operando: ") ;scanf (" %f",&op2) ;
printf  ("") ;
printf  ("La moltiplicazione tra op1 %f e op2 %f vale op1*op2 %f",op1,op2,op1*op2) ;
}
void do_div_int () {
int op1,op2;
printf  ("\n(3) DIVISIONE INTERA") ;
printf ("\nInserisci il primo operando: ") ;scanf (" %d",&op1) ;
printf ("Inserisci il secondo operando: ") ;scanf (" %d",&op2) ;
printf  ("") ;
printf  ("La divisione intera tra op1 %d e op2 %d vale op1/op2 %d",op1,op2,op1/op2) ;
}
void do_pow () {
int op1,op2;
printf  ("\n(4) POTENZA") ;
printf ("\nInserisci la base: ") ;scanf (" %d",&op1) ;
printf ("Inserisci l'esponente: ") ;scanf (" %d",&op2) ;
printf  ("") ;
printf  ("La potenza di op1 %d elevato a op2 %d vale op1^op2 %d",op1,op2,power(op1,op2)) ;
}
int  recursive_fib (int n) {
if (n==1) {
return 0;
}
;
if (n==2) {
return 1;
}
;
return recursive_fib (n-1) +recursive_fib (n-2) ;
}
int  iterative_fib (int n) {
if (n==1) {
return 0;
}
;
if (n==2) {
return 1;
}
;
if (n>2) {
int i = 3,res = 1,prev = 0;
while  (i<=n) {
int tmp = res;
res = res+prev;
;
prev = tmp;
;
i = i+1;
;
}
;
return res;
}
;
return 1;
}
void do_fib (int recursive) {
int n;
char* message = (char*) malloc (101);
printf  ("\n(5) FIBONACCI") ;
printf ("\nInserisci n: ") ;scanf (" %d",&n) ;
printf  ("") ;
message = strcat (convertS (strcat (convertS ("Il numero di Fibonacci in posizione ") ,convert (n) ) ) ,convertS (" vale ") ) ;
;
if (recursive) {
message = strcat (convertS (message) ,convert (recursive_fib (n) ) ) ;
;
}
else{
message = strcat (convertS (message) ,convert (iterative_fib (n) ) ) ;
;
}
;
printf  ("message :%s",message) ;
}
void do_operation (int choose) {
if (choose==1) {
do_sum () ;
}
else{
if (choose==2) {
do_mul () ;
}
else{
if (choose==3) {
do_div_int () ;
}
else{
if (choose==4) {
do_pow () ;
}
else{
if (choose==5) {
do_fib (1) ;
}
else{
if (choose==6) {
do_fib (0) ;
}
;
}
;
}
;
}
;
}
;
}
;
}
void print_continua (int *continua) {
char* in = (char*) malloc (101);
printf ("Vuoi continuare? (s/n) --> ") ;scanf (" %s",in) ;
if (strcmp (in,"s") == 0) {
*continua = 1;
;
}
else{
*continua = 0;
;
}
;
}
int main () {
int choose = 0;int continua = 1;
while  (continua) {
choose = print_menu () ;
;
if (choose==0) {
continua = 0;
;
}
else{
do_operation (choose) ;
print_continua (&continua) ;
}
;
}
;
return 0;}
