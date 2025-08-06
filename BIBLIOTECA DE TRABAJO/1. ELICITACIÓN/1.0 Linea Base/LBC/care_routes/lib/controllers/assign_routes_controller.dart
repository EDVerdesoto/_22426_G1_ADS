import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../domain/usable_route.dart';
import '../domain/vehicle_with_driver.dart';
import '../data/daos/routes_dao.dart';
import '../providers/database_providers.dart';

class AssignRoutesState {
  const AssignRoutesState({this.selected = const [], this.dates = const {}});
  final List<VehicleWithDriver> selected;
  final Map<int, DateTime?> dates;

  AssignRoutesState copyWith({
    List<VehicleWithDriver>? selected,
    Map<int, DateTime?>? dates,
  }) => AssignRoutesState(
    selected: selected ?? this.selected,
    dates: dates ?? this.dates,
  );
}

class AssignRoutesController extends StateNotifier<AssignRoutesState> {
  final Ref _ref;

  AssignRoutesController(this._ref) : super(const AssignRoutesState());

  /// Añade o quita el vehículo de la lista seleccionada
  void toggleVehicle(VehicleWithDriver v) {
    final list = [...state.selected];
    list.contains(v) ? list.remove(v) : list.add(v);
    state = state.copyWith(selected: list);
  }

  void setDate(VehicleWithDriver v, DateTime date) {
    final newDates = Map<int, DateTime?>.from(state.dates)
      ..[v.idVehicle] = date;
    state = state.copyWith(dates: newDates);
  }

  /// Verifica que haya vehículos seleccionados
  void _validateSelectedVehicles() {
    if (state.selected.isEmpty) {
      throw Exception('No vehicles selected for route assignment.');
    }
  }

  //Guarda la ruta asignada
  void saveRoute(UsableRoute routeData) {
    final routesDao = _ref.read(routesDaoProvider);
    final stopsDao = _ref.read(stopsDaoProvider);
    final routeAssignmentsDao = _ref.read(routeAssigmentsDaoProvider);

    // Verifica que haya vehículos seleccionados
    _validateSelectedVehicles();

    // Guarda la ruta
    final routeId = routesDao.insertRoute();

    final selectedVehicles = state.selected;
    if (selectedVehicles.isEmpty) {
      throw Exception('No vehicles selected for route assignment.');
    }
  }
}

final assignRoutesControllerProvider =
    StateNotifierProvider<AssignRoutesController, AssignRoutesState>(
      (ref) => AssignRoutesController(),
    );
