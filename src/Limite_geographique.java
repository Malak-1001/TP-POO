
public class Limite_geographique
{
    private float r;
    private float x;
     private float y;
    public float get_r(){return r;}
    public float get_x(){return x;}
    public float get_y(){return y;}
    /**
     * coordone x puis y puis le rayon
     */
    public float[] get_limite(){
        float[]lim=new float[2];
        lim[0]=x;
        lim[1]=y;
        lim[2]=r;
        return lim;
    }
}