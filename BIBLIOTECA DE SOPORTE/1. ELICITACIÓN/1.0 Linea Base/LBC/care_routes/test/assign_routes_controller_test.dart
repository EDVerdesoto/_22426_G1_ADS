import 'package:flutter_test/flutter_test.dart';
import '../lib/controllers/assign_routes_controller.dart';
import '../lib/domain/vehicle_with_driver.dart';

void main() {
  group('AssignRoutesController', () {
    late AssignRoutesController controller;
    late VehicleWithDriver vehicle1;
    late VehicleWithDriver vehicle2;

    setUp(() {
      controller = AssignRoutesController();
      vehicle1 = VehicleWithDriver(
        idVehicle: 1,
        idDriver: 10,
        licensePlate: 'ABC123',
        model: 'ModelX',
        driverName: 'John Doe',
        idNumber: 'ID1001',
      );
      vehicle2 = VehicleWithDriver(
        idVehicle: 2,
        idDriver: 20,
        licensePlate: 'XYZ789',
        model: 'ModelY',
        driverName: 'Jane Smith',
        idNumber: 'ID2002',
      );
    });

    test('toggleVehicle adds a vehicle if not selected', () {
      controller.toggleVehicle(vehicle1);
      expect(controller.state.selected, contains(vehicle1));
    });

    test('toggleVehicle removes a vehicle if already selected', () {
      controller.toggleVehicle(vehicle1);
      controller.toggleVehicle(vehicle1);
      expect(controller.state.selected, isNot(contains(vehicle1)));
    });

    test('setDate assigns a date to a vehicle', () {
      final date = DateTime(2025, 7, 29);
      controller.toggleVehicle(vehicle1);
      controller.setDate(vehicle1, date);
      expect(controller.state.dates[vehicle1.idVehicle], date);
    });

    test('setDate updates date for the same vehicle', () {
      final date1 = DateTime(2025, 7, 29);
      final date2 = DateTime(2025, 8, 1);
      controller.toggleVehicle(vehicle1);
      controller.setDate(vehicle1, date1);
      controller.setDate(vehicle1, date2);
      expect(controller.state.dates[vehicle1.idVehicle], date2);
    });
  });
}
