#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>

char* convert (int n) {
    char* to_ret = (char *) malloc (101);
    sprintf(to_ret,"%d",n);
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
int sceltaop,tipoop,num1,num2,ri;
float rnum1,rnum2,rr;

int  opInt (int num1,int num2,int tipoop,int *ri) {
int result;
if (tipoop==1) {
result = num1+num2;
;
}
;
if (tipoop==2) {
result = num1-num2;
;
}
;
if (tipoop==3) {
result = num1*num2;
;
}
;
if (tipoop==4) {
result = num1/num2;
;
}
;
return result;
}
float  opReal (float rnum1,float rnum2,int tipoop,int *ri) {
float result;
if (tipoop==1) {
result = rnum1+rnum2;
;
}
;
if (tipoop==2) {
result = rnum1-rnum2;
;
}
;
if (tipoop==3) {
result = rnum1*rnum2;
;
}
;
if (tipoop==4) {
result = rnum1/rnum2;
;
}
;
return result;
}
int main () {
int c = 1;
int result;
float res;
int n = 1;
while  (c==1) {
printf  ("Scegli l'operazione:") ;
printf  ("1 addizione") ;
printf  ("2 sottrazione") ;
printf  ("3 moltiplicazione") ;
printf  ("4 divisione") ;
scanf (" %d",&sceltaop) ;
printf  ("Scegli il tipo:") ;
printf  ("5 integer") ;
printf  ("6 real") ;
scanf (" %d",&tipoop) ;
if (tipoop==5) {
printf  ("Inserisci il primo numero") ;
scanf (" %d",&num1) ;
printf  ("Inserisci il secondo numero") ;
scanf (" %d",&num2) ;
result = opInt (num1,num2,sceltaop,&ri) ;
;
}
;
if (tipoop==6) {
printf  ("Inserisci il primo numero") ;
scanf (" %f",&rnum1) ;
printf  ("Inserisci il secondo numero") ;
scanf (" %f",&rnum2) ;
res = opReal (rnum1,rnum2,sceltaop,&ri) ;
;
}
;
printf  ("Digita 1 per continuare o 0 per terminare") ;
scanf (" %d",&c) ;
}
;
return 0;}
