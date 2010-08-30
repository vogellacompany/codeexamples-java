package de.vogella.jdt.codeanalysis.model;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;

public class MethodInformation {
	private final String methodName;
	private final int numberOfCalls;
	private final ICompilationUnit cu;
	private final IMethod method;

	public MethodInformation(String methodName, int numberOfCalls,
			ICompilationUnit cu, IMethod method) {
		this.methodName = methodName;
		this.numberOfCalls = numberOfCalls;
		this.cu = cu;
		this.method = method;
	}

	/**
	 * @return the method
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @return the numberOfCalls
	 */
	public int getNumberOfCalls() {
		return numberOfCalls;
	}

	/**
	 * @return the cu
	 */
	public ICompilationUnit getResource() {
		return cu;
	}

	/**
	 * @return the method
	 */
	public IMethod getMethod() {
		return method;
	}

}
