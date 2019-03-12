package com.mehmet.hesapmakinasi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView yaziAlani, yaziAlaniIki;
    private Button bir , iki , uc ,dort ,bes ,alti ,yedi ,sekiz ,dokuz ,sifir,carpma ,
            toplam ,bolme ,cikarma,nokta, sil , esittir, delete;
    private ArrayList <String> girdiler = new ArrayList<>();
    private String islemBir, islemTip, operator;
    private int islemSayac=0;
    private int butonSayac=0;
    private double sayiBir, sayiIki, sonuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        yaziAlani = (TextView) findViewById(R.id.islemAlan);
        yaziAlaniIki = (TextView) findViewById(R.id.islemAlanIki);
        bir.setOnClickListener(this);  iki.setOnClickListener(this);
        uc.setOnClickListener(this); dort.setOnClickListener(this);
        bes.setOnClickListener(this); alti.setOnClickListener(this);
        yedi.setOnClickListener(this); sekiz.setOnClickListener(this);
        dokuz.setOnClickListener(this); carpma.setOnClickListener(this);
        toplam.setOnClickListener(this); bolme.setOnClickListener(this);
        cikarma.setOnClickListener(this); sil.setOnClickListener(this);
        nokta.setOnClickListener(this); esittir.setOnClickListener(this);
        sifir.setOnClickListener(this); delete.setOnClickListener(this);
    }

    public void init (){
        //NESNELER İLE TASARIM İDLERİ İLİŞKİLENDİREN BAŞLANGIÇ METODU
        bir = (Button) findViewById(R.id.bir);
        iki = (Button) findViewById(R.id.iki);
        uc = (Button) findViewById(R.id.üc);
        dort = (Button) findViewById(R.id.dort);
        bes = (Button) findViewById(R.id.bes);
        alti = (Button) findViewById(R.id.alti);
        yedi = (Button) findViewById(R.id.yedi);
        sekiz = (Button)findViewById(R.id.sekiz);
        dokuz = (Button)findViewById(R.id.dokuz);
        carpma = (Button)findViewById(R.id.carpma);
        toplam = (Button)findViewById(R.id.toplama);
        bolme = (Button)findViewById(R.id.bolum);
        cikarma = (Button) findViewById(R.id.cikarma);
        nokta = (Button)findViewById(R.id.nokta);
        sil = (Button) findViewById(R.id.sil);
        esittir = (Button) findViewById(R.id.esittir);
        sifir = (Button) findViewById(R.id.sifir);
        delete= (Button) findViewById(R.id.delete);

    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.bir :  { ekranaYaz("1", yaziAlani); islemBir = "1";  break;}
                case R.id.iki : { ekranaYaz("2", yaziAlani);islemBir = "2"; break;}
                case R.id.üc : { ekranaYaz("3", yaziAlani);islemBir = "3"; break;}
                case R.id.dort : { ekranaYaz("4", yaziAlani); islemBir = "4"; break;}
                case R.id.bes : {ekranaYaz("5", yaziAlani); islemBir = "5"; break;}
                case R.id.alti : {ekranaYaz("6", yaziAlani); islemBir = "6"; break;}
                case R.id.yedi : {ekranaYaz("7", yaziAlani); islemBir = "7"; break;}
                case R.id.sekiz : {ekranaYaz("8", yaziAlani); islemBir = "8"; break;}
                case R.id.dokuz : {ekranaYaz("9", yaziAlani); islemBir = "9"; break;}
                case R.id.sifir : {ekranaYaz("0", yaziAlani); islemBir = "0"; break;}
                case R.id.nokta : {ekranaYaz(".", yaziAlani);islemBir = "."; break;}
                case R.id.sil : { karakterSil(yaziAlani); break; }
                case R.id.delete : {ekranTemizle(yaziAlani); ekranTemizle(yaziAlaniIki); break;}
                //OPERATÖR SEÇİMİ YAPILIRSA BUTONSAYAC -1 YAPILIR.

                case R.id.esittir : {islemSecimi(operator);  break;}
                case R.id.carpma : { islemSecimi("*");  break;}
                case R.id.toplama : { islemSecimi("+"); break;}
                case R.id.bolum : { islemSecimi("/");  break;}
                case R.id.cikarma : {islemSecimi("-");  break;}
            }
    }
    public void islemSecimi(String secim){// OPERATÖR SEÇİMİNİ YAPACAK FONK.
        islemSayac++;
        operator = secim;
        if (islemSayac==1 || islemSayac>2){//İLK DEFA VEYA BİR İŞLEM YAPILDIKTAN SONRA YAPILAN SECİM
            sayiBir=sayiAl(yaziAlani);
            if (islemSayac>2){//MÜKERRER SEÇİMLERDE ÖRNEK 3+2 İŞLEMİ YAPILDI SONRASINDA - SEÇİLDİĞİ ZAMAN
                ekranYaz(yaziAlani,yaziAlaniIki);
                islemSayac=1;
            }
            ekranaYaz(operator, yaziAlani);
            ekranaYaz(yaziAlani,yaziAlaniIki);
        }
        if (islemSayac==2){
            sayiIki=sayiAl(yaziAlani);
            ekranaYaz(yaziAlani,yaziAlaniIki);
            sonuc = islemYap(operator);
            ekranaYaz(String.valueOf(sonuc),yaziAlani);
        }

    }

    public double sayiAl (TextView t) { //TEXTVİEW ALANINDAKİ SAYI ALIR ALINIR
            Double sayi = Double.valueOf((String) t.getText());
            return sayi;
    }

    public double islemYap(String islemTip){//SEÇİLEN OPERATÖRE GÖRE İŞLEM YAPAR
        double sonuc=0;
        switch (islemTip){
            case "+":
                sonuc=sayiBir+sayiIki; break;
            case "-":
                sonuc=sayiBir-sayiIki; break;
            case "*":
                sonuc=sayiBir*sayiIki;break;
            case "/":
                sonuc=sayiBir/sayiIki; break;
        }
        return sonuc;
    }

    public void ekranaYaz(String sayi, TextView t){//BASILAN SAYILARI PEŞPEŞE EKLEYEREK EKRANA BASAR
       sayi= (String) t.getText()+sayi;
       t.setText("");
       t.setText(sayi);
    }
    public void ekranaYaz(TextView t1 , TextView t2){ //ilk satır yukarı satıra yazılır
        t2.setText((String)t2.getText()+ (String) t1.getText());
        t1.setText("");

    }
    public void ekranYaz(TextView t1 , TextView t2){ // İŞLEM SONUCUNDA DEVAM EDİLMEK İSTENİRSE SONUCU ÜSTTE ATAR
        String sayi= (String) t1.getText();
        t2.setText(sayi);
        t1.setText("");

    }

    public void ekranTemizle (TextView t){ //GÖNDERİLEN TEXTVİEW TEMİZLER
        t.setText("");
    }

    public void karakterSil (TextView t){ // C TUŞUNA BASILDIĞINDA SONDAKİ KARAKTERİ TEMİZLER
        String ekranVeri = (String) t.getText();
        ekranVeri = ekranVeri.substring(0,ekranVeri.length()-1);
        t.setText("");
        t.setText(ekranVeri);
    }
}
