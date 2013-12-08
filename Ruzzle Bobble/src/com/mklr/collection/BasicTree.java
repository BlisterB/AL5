package com.mklr.collection;

/**
 * Interface d'arbre.
 * @author Loic Runarvot
 * @author Mehdi Khelifi
 */
public interface BasicTree<T>
{

    /**
     * Ajoute un fils newValue au noeud courants selon l'implémentation.
     * @param newValue element associé à l'arbre.
     */
    public void add(T newValue);
    

    /**
     * Supprime le fils égal à valueToRemove au noeud courant.
     * @param valueToRemove
     */
    public void remove(T valueToRemove);
   

    /**
     * Recherche dans les fils si valueToSearch existe.
     * @param valueToSearch valeur associée à l'arbre.
     * @return true si le fils existe.
     */
    public boolean childExist(T valueToSearch);

    /**
     * Retourne le fils associé au noeud courant par la clé neededChild.
     * @param neededChild élément recherché.
     * @return le fils.
     */
    public BasicTree<T> getChild(T neededChild);
}
