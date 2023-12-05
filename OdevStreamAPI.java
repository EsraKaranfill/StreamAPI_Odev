package com.bilgeadam.lesson030;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OdevStreamAPI {
    List<Film> filmler;
    // 1
    public List<Film> puanaGoreSirala5iAlma(){
        System.out.println("------------------------");
        List<Film> enIyiFilmler=filmler.stream()
                .sorted(Comparator.comparingDouble(Film::getPuan))
                .limit(5)
                .collect(Collectors.toList());
        System.out.println("En iyi 5 film: ");
        return enIyiFilmler;
    }
    // 2
    public void tureGoreOrtPuan(ETur tur){
        System.out.println("-------------------------");
        double ortPuan=filmler.stream()
                .filter(x->x.getTur().equals(tur))
                .mapToDouble(Film::getPuan).average().orElse(0.0);
        System.out.println(ortPuan);
    }
    // 3
    public void tureGoreSiralama(){
        System.out.println("----------------------------");
        List<ETur> siraliTurler=filmler.stream().map(Film::getTur)
                .distinct().sorted().collect(Collectors.toList());
        siraliTurler.forEach(System.out::println);
    }
    // 4
    public void hasilataGoreListele(int min,int max){
        System.out.println("---------------------------");
        List<Film> hasilatAraligindaFilmler= filmler.stream()
                .filter(f->f.getHasilat() >= min && f.getHasilat() <= max)
                .collect(Collectors.toList());
        hasilatAraligindaFilmler.forEach(System.out::println);
    }
    // 5
    public void isimUlkeEslestirme(){
        System.out.println("---------------------------");
        Map<String, String> yonetmenIsimUlkeMap = filmler.stream()
                .collect(Collectors.toMap(
                        f -> f.getYonetmen().getIsim(),
                        f -> f.getYonetmen().getUlke()));
        yonetmenIsimUlkeMap.forEach((k,v)-> System.out.println(k+" "+v));
    }
    // 6 YI YAPAMADIM HOCAM
    // 7
    public void animasyonHasilat(){
        System.out.println("---------------------------");
        double animasyonHasilat=filmler.stream()
                .filter(f->f.getTur().equals(ETur.ANIMASYON))
                .mapToDouble(Film::getHasilat).sum();
        System.out.println(animasyonHasilat);
    }
    // 8
    public void yonetmeninFilmSayisi(Yonetmen yonetmen){
        System.out.println("---------------------------");
        int filmSayisi= (int) filmler.stream()
                .filter(x->x.getYonetmen().getIsim().equalsIgnoreCase(String.valueOf(yonetmen)))
                .count();
        System.out.println(filmSayisi);
    }
    // 9
    public void ulkedenYonetmenFilmSayisi(String ulke){
        System.out.println("---------------------------");
        Map<Object, Long> yonetmenFilmSayisi=filmler.stream()
                .filter(x->x.getYonetmen().getUlke().equalsIgnoreCase(ulke))
                .collect(Collectors.groupingBy(y->y.getYonetmen().getIsim(),
                        Collectors.counting()));
        yonetmenFilmSayisi.forEach((k,v)-> System.out.println(k+" "+v));
    }
    // 10
    // 11
    public void yonetmenPuanOrt(){
        System.out.println("---------------------------");
        Map<String, Double> yonetmenPuanOrt=filmler.stream()
                .collect(Collectors.groupingBy(
                        x->x.getYonetmen().getIsim(),
                        Collectors.averagingDouble(Film::getPuan)
                ));
        yonetmenPuanOrt.forEach((k,v)-> System.out.println(k+" "+v));
    }

    public static void main(String[] args) {
        Uygulama uygulama=new Uygulama();
        uygulama.baslangicVerisiOlustur();
        OdevStreamAPI odevStreamAPI=new OdevStreamAPI();
        odevStreamAPI.puanaGoreSirala5iAlma();
        odevStreamAPI.tureGoreOrtPuan(ETur.BILIMKURGU);
        odevStreamAPI.animasyonHasilat();
        odevStreamAPI.isimUlkeEslestirme();
        odevStreamAPI.hasilataGoreListele(6000000,9000000);
        odevStreamAPI.tureGoreSiralama();
        odevStreamAPI.ulkedenYonetmenFilmSayisi("Türkiye");
        odevStreamAPI.yonetmenPuanOrt();

    }


}
