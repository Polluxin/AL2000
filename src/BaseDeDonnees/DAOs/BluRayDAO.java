package BaseDeDonnees.DAOs;

import Metier.GestionLocation.BluRay;

import java.sql.Connection;

// TODO: Tester

@SuppressWarnings("unused")
public class BluRayDAO extends DAO<BluRay>{

    private int idMachineAssocie;

    public BluRayDAO(Connection conn) {
        super(conn);
    }

    public void setIdMachineAssocie(int idMachineAssocie) {
        this.idMachineAssocie = idMachineAssocie;
    }

    @Override
    public boolean creer(BluRay obj) {
        // TODO
        return false;
    }

    @Override // Utilisé lors du rendu de BluRay
    public BluRay lire(int id) {
        // Impossible
        return null;
    }

    @Override
    public boolean modifier(BluRay obj) {
        // Impossible
        return false;
    }

    @Override
    public boolean supprimer(BluRay obj) {
        // TODO
        return false;
    }
}
