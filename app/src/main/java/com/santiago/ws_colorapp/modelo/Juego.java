package com.santiago.ws_colorapp.modelo;

public class Juego {

    private int totalPalabras;
    private int correctas;
    private int incorrectas;
    private float reaccion;
    //juego configuracion
    private int tiempoPalabra;
    private int tiempoTotal;
    private int intentos;

    public Juego() {
    }

    public Juego(int totalPalabras, int correctas, int incorrectas, float reaccion, int tiempoPalabra, int tiempoTotal, int intentos) {
        this.totalPalabras = totalPalabras;
        this.correctas = correctas;
        this.incorrectas = incorrectas;
        this.reaccion = reaccion;
        this.tiempoPalabra = tiempoPalabra;
        this.tiempoTotal = tiempoTotal;
        this.intentos = intentos;
    }

    public int getTotalPalabras() {
        return totalPalabras;
    }

    public void setTotalPalabras(int totalPalabras) {
        this.totalPalabras = totalPalabras;
    }

    public int getCorrectas() {
        return correctas;
    }

    public void setCorrectas(int correctas) {
        this.correctas = correctas;
    }

    public int getIncorrectas() {
        return incorrectas;
    }

    public void setIncorrectas(int incorrectas) {
        this.incorrectas = incorrectas;
    }

    public float getReaccion() {
        return reaccion;
    }

    public void setReaccion(float reaccion) {
        this.reaccion = reaccion;
    }

    public int getTiempoPalabra() {
        return tiempoPalabra;
    }

    public void setTiempoPalabra(int tiempoPalabra) {
        this.tiempoPalabra = tiempoPalabra;
    }

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(int tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }
}
