/*
 * Native C-Implementierung des Apfelmännchen-Algorithmus
 * für die Verwendung aus Android Java heraus
 * Übersetzung mit ndk-build
 */
#include <jni.h>

// Makros für Festkomma-Integer-Arithmetik
#define Fixed jint
// 25 bit für Nachkommastellen, 1 bit fürs Vorzeichen, 6 bit für Ganzzahlen (bis 63)
#define  FIXED_BITS           25
#define  FIXED_ONE            (1 << FIXED_BITS)
#define  FIXED_MUL(x,y)       (((jlong)(x) * (y)) >> FIXED_BITS)
#define  FIXED_FROM_INT(x)    ((x) << FIXED_BITS)
#define  FIXED_FROM_FLOAT(x)  ((Fixed)((x)*(jfloat)FIXED_ONE))

#define MAXQ FIXED_FROM_INT(4)

// Apfelmännchen in Integer-Arithmetik für einen Punkt
// gibt Iterationswert 1..maxiter zurück
static jint iteratePointFixed(const Fixed xx,const Fixed yy,const jint maxiter) {
    Fixed x = 0;
	Fixed y = 0;
	jint iter = 0;
	Fixed x2 = 0;
	Fixed y2 = 0;
	do {
		y = FIXED_MUL(x,y);
		y<<=1; // *2
		y+=yy;
		x = x2-y2+xx;
		x2=FIXED_MUL(x,x);
		y2=FIXED_MUL(y,y);
		iter++;
	} while (x2+y2 < MAXQ && iter < maxiter);
	return iter;
}
// JNI-Methode für Klasse com.linkesoft.apfel3.UpdateThread
// private native static void iteratePixelsJNI(int pixels[],int colormap[],int maxiter,int w,int h,float xmin,float ymin,float xmax,float ymax);
// jpixels ist ein Array der Größe mindestens h*w
// jcolormap ist eine Farbtabelle der Größe maxiter
JNIEXPORT void JNICALL 
Java_com_linkesoft_apfel3_UpdateThread_iteratePixelsJNI(JNIEnv * env, jobject  obj, jintArray *jpixels,jintArray *jcolormap,jint maxiter,jint w,jint h,jfloat xmin,jfloat ymin,jfloat xmax,jfloat ymax) {
	jint *pixels=(*env)->GetIntArrayElements(env, jpixels, 0); // konvertiere in C-Array
	jint *colormap=(*env)->GetIntArrayElements(env, jcolormap, 0);
	int indpixel=0;
	jint y=0;
	jint x;
	Fixed dy=FIXED_FROM_FLOAT((ymax-ymin)/(jfloat)h);
	Fixed dx=FIXED_FROM_FLOAT((xmax-xmin)/(jfloat)w);
	Fixed yp=FIXED_FROM_FLOAT(ymin);
	for(y=0;y<h;y++) {
		Fixed xp=FIXED_FROM_FLOAT(xmin);
		for(x=0;x<w;x++) {
			jint iter=iteratePointFixed(xp,yp,maxiter);
			pixels[indpixel++]=colormap[iter-1];
			xp+=dx;
		}
		yp+=dy;
	}
	(*env)->ReleaseIntArrayElements(env,jcolormap,colormap, JNI_ABORT); // gib Speicher frei ohne zurückzukopieren
	(*env)->ReleaseIntArrayElements(env,jpixels,pixels, 0); // gib Speicher frei, kopiere Daten zurück nach Java
}

