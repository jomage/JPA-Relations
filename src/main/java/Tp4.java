import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import model.Adresse;
import model.Banque;
import model.Client;
import model.Compte;
import repository.PersistenceHelper;

public class Tp4 {

    public static void main(String[] args) {
        insertEntities();
    }
    
    private static void insertEntities() {
        EntityManager em = PersistenceHelper.getEntityManager();
        PersistenceHelper.beginTx(em);

        Banque banque = new Banque();
        banque.setNom("Banque Principale");
        em.persist(banque);

        Compte compte1 = new Compte();
        compte1.setNumero("10000");
        compte1.setSolde(500D);
        Compte compte2 = new Compte();
        compte2.setNumero("10001");
        compte2.setSolde(321D);
        em.persist(compte1);
        em.persist(compte2);
        
        List<Compte> listeComptes1 = new ArrayList<>();
        listeComptes1.add(compte1);
        listeComptes1.add(compte2);
        List<Compte> listeComptes2 = new ArrayList<>();
        listeComptes2.add(compte2);

        Adresse adresseC1 = new Adresse();
        adresseC1.setNumero(42);
        adresseC1.setRue("Rue du test");
        adresseC1.setVille("La-Teste-De-Buch");
        adresseC1.setCodePostal(11111);
        Adresse adresseC2 = new Adresse();
        adresseC2.setNumero(99);
        adresseC2.setRue("Rue du Code");
        adresseC2.setVille("Codond-les-bois");
        adresseC2.setCodePostal(22222);
        em.persist(adresseC1);
        em.persist(adresseC2);

        Client client1 = new Client();
        client1.setNom("PREMIER");
        client1.setPrenom("Client");
        client1.setBanque(banque);
        client1.setComptes(listeComptes1);
        client1.setAdresse(adresseC1);
        Client client2 = new Client();
        client2.setNom("SECOND");
        client2.setPrenom("Client");
        client2.setBanque(banque);
        client2.setComptes(listeComptes2);
        client1.setAdresse(adresseC2);
        em.persist(client1);
        em.persist(client2);
        
        PersistenceHelper.commitTxAndClose(em);
    }

}
