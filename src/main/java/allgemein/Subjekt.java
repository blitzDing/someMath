package allgemein;


public interface Subjekt <G> 
{

	void addBeholders(Beholder<G> b);
	void removeBeholders(Beholder<G> b);
	void informBeholders(G g);
}
