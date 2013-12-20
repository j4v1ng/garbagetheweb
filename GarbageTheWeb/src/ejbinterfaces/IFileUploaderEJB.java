package ejbinterfaces;

import entities.Garbage;

public interface IFileUploaderEJB {

	public abstract Garbage uploadGarbage(Garbage garbage);

}