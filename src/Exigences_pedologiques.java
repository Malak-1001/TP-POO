

public class Exigences_pedologiques
{
    private final float ph_min;
    private final float ph_max;
    private final float humidite_min;
    private final float humidite_max;

    public Exigences_pedologiques(float ph_min, float ph_max, float humidite_min, float humidite_max){
        if (ph_min > ph_max) throw new IllegalArgumentException("ph_min doit etre <= ph_max");
        if (humidite_min > humidite_max) throw new IllegalArgumentException("humidite_min doit etre <= humidite_max");
        this.ph_min = ph_min;
        this.ph_max = ph_max;
        this.humidite_min = humidite_min;
        this.humidite_max = humidite_max;
    
    }

    public float get_ph_min(){return ph_min;}
    public float get_ph_max(){return ph_max;}
    public float get_humidite_min(){return humidite_min;}
    public float get_humidite_max(){return humidite_max;}

    
}
