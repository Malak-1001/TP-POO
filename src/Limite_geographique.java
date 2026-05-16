
public class Limite_geographique
{
    private float r;
    private float x;
    private float y;
    public float get_r(){return r;}
    public float get_x(){return x;}
    public float get_y(){return y;}

    /**
     * Constructeur de la 
     * @param r
     * @param x
     * @param y
     */
    public Limite_geographique(float r,float x, float y){
    this.x=x;
    this.y=y;
    this.r=r;
   
    }
    /**
     * coordonee x puis y puis le rayon
     */
    public float[] get_limite(){
        float[]lim=new float[3];
        lim[0]=x;
        lim[1]=y;
        lim[2]=r;
        return lim;
    }
    public Limite_geographique(float r,float x, float y){
    this.x=x;
    this.y=y;
    this.r=r;
    
    }
}
