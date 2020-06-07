package com.gokhan.akillisistem.Model;

public class Chart {
    private String tarih;
    private String gorevPuan;
    private String verilenGorev;
    private int sayi;
    public Chart(String gorevPuan){
        this.gorevPuan=gorevPuan;

    }
    public Chart(String gorevPuan,String verilenGorev){
        this.gorevPuan=gorevPuan;
        this.verilenGorev=verilenGorev;
    }
    public Chart(String tarih,String gorevPuan,String verilenGorev,int sayi){
        this.tarih=tarih;
        this.gorevPuan=gorevPuan;
        this.verilenGorev=verilenGorev;
        this.sayi=sayi;
    }
    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getGorevPuan() {
        return gorevPuan;
    }

    public void setGorevPuan(String gorevPuan) {
        this.gorevPuan = gorevPuan;
    }

    public String getVerilenGorev() {
        return verilenGorev;
    }

    public void setVerilenGorev(String verilenGorev) {
        this.verilenGorev = verilenGorev;
    }

    public int getSayi() {
        return sayi;
    }

    public void setSayi(int sayi) {
        this.sayi = sayi;
    }
    public String toString(){
        return tarih + gorevPuan +verilenGorev +sayi;
    }
}
