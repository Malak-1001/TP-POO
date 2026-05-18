public class programme_alimentaire{
private String type_alimentation;
private double Quantite_par_repas ;
public programme_alimentaire(double quantite,String type_aliment){
Quantite_par_repas=quantite;
type_alimentation=type_aliment;

}
public programme_alimentaire(){
Quantite_par_repas=0;
type_alimentation="";

}

public String get_type_alimentation(){
    return type_alimentation;
}

public double get_quantite_par_repas(){
    return Quantite_par_repas;
}

@Override
public String toString(){
    return "type=" + type_alimentation + ", quantite_par_repas=" + Quantite_par_repas;
}
public void info_program(){
System.out.println("type daliment :"+type_alimentation);
System.out.println("quantite :"+Quantite_par_repas+ "Kg");

}
}
