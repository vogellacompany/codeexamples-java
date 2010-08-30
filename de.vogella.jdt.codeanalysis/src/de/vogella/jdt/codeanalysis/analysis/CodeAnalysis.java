package de.vogella.jdt.codeanalysis.analysis;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;

import de.vogella.jdt.codeanalysis.model.MethodInformation;

public class CodeAnalysis {

	public static List<MethodInformation> calculate(IJavaProject project) {
		List<MethodInformation> list = new ArrayList<MethodInformation>();
			try {
				if (project.isOpen()) {

					IPackageFragment[] packages = project
							.getPackageFragments();
					// parse(JavaCore.create(project));
					for (IPackageFragment mypackage : packages) {
						if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
							for (ICompilationUnit unit : mypackage
									.getCompilationUnits()) {
								IType[] types = unit.getTypes();
								for (int i = 0; i < types.length; i++) {
									IType type = types[i];
									IMethod[] methods = type.getMethods();
									for (int j = 0; j < methods.length; j++) {
										IMethod method = methods[j];
										if (!method.isMainMethod()) {
											int number = performIMethodSearch(method);

											if (number == 0) {
												MethodInformation metric = new MethodInformation(
														method.getElementName(),
														number, unit, method);
												list.add(metric);
											}

										}

									}

								}

							}
						}

					}
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		return list;
	}

	private static int performIMethodSearch(IMethod method)
			throws CoreException {
		SearchPattern pattern = SearchPattern.createPattern(method,
				IJavaSearchConstants.REFERENCES);
		IJavaSearchScope scope = SearchEngine.createWorkspaceScope();
		MySearchRequestor requestor = new MySearchRequestor();
		SearchEngine searchEngine = new SearchEngine();
		searchEngine.search(pattern, new SearchParticipant[] { SearchEngine
				.getDefaultSearchParticipant() }, scope, requestor, null);
		return requestor.getNumberOfCalls();

	}
}
