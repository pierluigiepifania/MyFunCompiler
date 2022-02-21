#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>

char* convert (int n) {
    char* to_ret = (char *) malloc (101);
    sprintf(to_ret,"%d",n);
    return to_ret;
}
char* convert (float n) {
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
int c = 1;

float  sommac (int a,float b,char* size) {
float result;
result = a+b+c;
;
if (result>100) {
char* valore = "grande";
size = valore;
;
}
else{
char* valore = "piccola";
size = valore;
;
}
;
return result;
}
void stampa (char* messaggio) {
int i = 1;
while  (i<=4) {
int incremento = 1;
printf  ("") ;
i = i+incremento;
;
}
;
printf  ("messaggio :%s",messaggio) ;
}
int main () {
int a = 1;float b = 2.2;
char* taglia;
char* ans = "no";
float risultato = sommac (a,b,taglia) ;
stampa (strcat (convertS (strcat (convertS (strcat (convertS (strcat (convertS (strcat (convertS (strcat (convertS (strcat (convertS ("la somma di ") ,convert (a) ) ) ,convertS (" e ") ) ) ,convertF (b) ) ) ,convertS (" incrementata di ") ) ) ,convert (c) ) ) ,convertS (" è ") ) ) ,convertS (taglia) ) ) ;
stampa (strcat (convertS ("ed è pari a ") ,convertF (risultato) ) ) ;
printf  ("vuoi continuare? (si/no)") ;
scanf (" %s",ans) ;
while  (strcmp (ans,"si") == 0) {
printf ("inserisci un intero:") ;scanf (" %d",&a) ;
printf ("inserisci un reale:") ;scanf (" %f",&b) ;
risultato = sommac (a,b,taglia) ;
;
stampa (strcat (convertS (strcat (convertS (strcat (convertS (strcat (convertS (strcat (convertS (strcat (convertS (strcat (convertS ("la somma di ") ,convert (a) ) ) ,convertS (" e ") ) ) ,convertF (b) ) ) ,convertS (" incrementata di ") ) ) ,convert (c) ) ) ,convertS (" è ") ) ) ,convertS (taglia) ) ) ;
stampa (strcat (convertS (" ed è pari a ") ,convertF (risultato) ) ) ;
printf ("vuoi continuare? (si/no):\t") ;scanf (" %s",ans) ;
}
;
printf  ("ans :%s",ans) ;
printf  ("") ;
printf  ("ciao") ;
return 0;}
