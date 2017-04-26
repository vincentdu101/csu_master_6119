
/**
 * Write a description of class PieceWorker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PieceWorker extends Employee
{
    // instance variables - replace the example below with your own
    private double salary = 0.0;
    private double wagePerPiece;
    private int pieces;
    
    /**
     * Constructor for objects of class PieceWorker
     */
    public PieceWorker(String firstName, String lastName, String socialSecurityNumber, double wagePerPiece, int pieces)
    {
        // initialise instance variables
        super(firstName, lastName, socialSecurityNumber);
        this.wagePerPiece = wagePerPiece;
        this.pieces = pieces;
        setWageSalary(wagePerPiece, pieces);
    }

    private void setWageSalary(double wagePerPiece, int pieces) {
        if (pieces > 0) {
            salary = wagePerPiece * pieces;
            salary = salary > 0 ? salary : 0;
        }
    }
    
    public double getSalary() {
        return salary;
    }
    
    public double earnings() {
        return getSalary();
    }
   
   public String toString()
   {
      return String.format( "%s: %s\n%s: $%,.2f; %s: %d", 
         "piece worker", super.toString(), 
         "wage per piece", wagePerPiece, 
         "pieces produced", pieces,
         "earned ", earnings());
   }
    
}
