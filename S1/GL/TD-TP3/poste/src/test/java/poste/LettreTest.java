package poste;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class LettreTest {
	// création des objets sous test
	Lettre lettre1;
	Lettre lettre2;
	Lettre lettre3;
	private static float tolerancePrix=0.001f;
	@BeforeEach
	public void setUp() {
		lettre1 = new Lettre("Le pere Noel",
				"famille Kirik, igloo 5, banquise nord",
				"7877", 25, 0.00018f, Recommandation.un, false);
		lettre2 = new Lettre("Le pere Noel",
				"famille Kouk, igloo 2, banquise nord",
				"5854", 18, 0.00018f, Recommandation.deux, true);
		lettre3 = new Lettre("Le pere Noel",
				"famille Kouk, igloo 2, banquise nord",
				"5854", 18, 0.00018f, Recommandation.zero, false);
	}

	@Test
	public void testLettreConstructeurDef() {
		Lettre lettreDefaut=new Lettre();
		assert(lettreDefaut.getOrigine()=="inconnue");
		assert(lettreDefaut.getDestination()=="inconnue");
		assert(lettreDefaut.getCodePostal()=="0000");
		assert(lettreDefaut.getPoids()==0);
		assert(lettreDefaut.getVolume()==0);
		assert(lettreDefaut.getTauxRecommandation()==Recommandation.zero);
		assert(lettreDefaut.isUrgence()==false);
	}
	@Test
	public void testToStringLettre() {
		assertEquals(lettre1.toString(),"Lettre 7877/famille Kirik, igloo 5, banquise nord/1/ordinaire");
		assertEquals(lettre2.toString(),"Lettre 5854/famille Kouk, igloo 2, banquise nord/2/urgence");
	}
	@Test
	public void testAffranchissement() {
		assert (Math.abs(lettre1.tarifAffranchissement()-1f)<tolerancePrix);
		assert (Math.abs(lettre2.tarifAffranchissement()-2.3f)<tolerancePrix);
		assert (Math.abs(lettre3.tarifAffranchissement()-0.5f)<tolerancePrix);
	}
	@Test
	public void tarifRemboursementTest() {
		assert (Math.abs(lettre1.tarifRemboursement()-1.5f)<tolerancePrix);
		assert (Math.abs(lettre2.tarifRemboursement()-15.0f)<tolerancePrix);
		assert (lettre3.tarifRemboursement()==0);
	}
	@Test
	public void afficheTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		System.setOut(ps);
		lettre1.affiche();
		assert(baos.toString().equals(lettre1.toString()+"\n"));//affiche utilise un println qui rajoute un "\n"
		}
}
