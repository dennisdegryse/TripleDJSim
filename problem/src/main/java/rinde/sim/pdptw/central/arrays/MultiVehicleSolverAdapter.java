/**
 * 
 */
package rinde.sim.pdptw.central.arrays;

import javax.measure.quantity.Duration;
import javax.measure.unit.Unit;

import rinde.sim.pdptw.central.GlobalStateObject;
import rinde.sim.pdptw.central.Solver;
import rinde.sim.pdptw.central.arrays.ArraysSolvers.MVArraysObject;
import rinde.sim.pdptw.common.ParcelDTO;

import com.google.common.collect.ImmutableList;

/**
 * Adapter for {@link MultiVehicleArraysSolver} to conform to the {@link Solver}
 * interface.
 * @author Rinde van Lon <rinde.vanlon@cs.kuleuven.be>
 */
public class MultiVehicleSolverAdapter implements Solver {

  private final MultiVehicleArraysSolver solver;
  private final Unit<Duration> outputTimeUnit;

  /**
   * @param solver The solver to use.
   * @param outputTimeUnit The time unit which is expected by the specified
   *          solver.
   */
  public MultiVehicleSolverAdapter(MultiVehicleArraysSolver solver,
      Unit<Duration> outputTimeUnit) {
    this.solver = solver;
    this.outputTimeUnit = outputTimeUnit;
  }

  @Override
  public ImmutableList<ImmutableList<ParcelDTO>> solve(GlobalStateObject state) {
    final MVArraysObject o = ArraysSolvers.toMultiVehicleArrays(state,
        outputTimeUnit);

    final SolutionObject[] sols = solver.solve(o.travelTime, o.releaseDates,
        o.dueDates, o.servicePairs, o.serviceTimes, o.vehicleTravelTimes,
        o.inventories, o.remainingServiceTimes, o.currentDestinations,
        o.currentSolutions);
    final ImmutableList.Builder<ImmutableList<ParcelDTO>> b = ImmutableList
        .builder();
    for (final SolutionObject sol : sols) {
      b.add(ArraysSolvers.convertSolutionObject(sol, o.index2parcel));
    }
    return b.build();
  }
}
