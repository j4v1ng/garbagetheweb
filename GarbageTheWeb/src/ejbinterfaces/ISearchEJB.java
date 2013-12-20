package ejbinterfaces;

import java.util.List;

import entities.Garbage;

public interface ISearchEJB {

	public abstract List<Garbage> findAllGarbage();

}