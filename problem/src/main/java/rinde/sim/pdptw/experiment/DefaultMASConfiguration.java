/**
 * 
 */
package rinde.sim.pdptw.experiment;

import rinde.sim.core.model.Model;
import rinde.sim.pdptw.common.AddDepotEvent;
import rinde.sim.pdptw.common.AddParcelEvent;
import rinde.sim.pdptw.common.DynamicPDPTWProblem.Creator;
import rinde.sim.util.SupplierRng;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

/**
 * Implementation of {@link MASConfiguration} which implements most method using
 * default values.
 * @author Rinde van Lon <rinde.vanlon@cs.kuleuven.be>
 */
public abstract class DefaultMASConfiguration implements MASConfiguration {

  @Override
  public ImmutableList<? extends SupplierRng<? extends Model<?>>> getModels() {
    return ImmutableList.of();
  }

  @Override
  public Optional<? extends Creator<AddDepotEvent>> getDepotCreator() {
    return Optional.absent();
  }

  @Override
  public Optional<? extends Creator<AddParcelEvent>> getParcelCreator() {
    return Optional.absent();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }
}
