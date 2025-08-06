import 'package:freezed_annotation/freezed_annotation.dart';

part 'usable_route.freezed.dart';

class Stop {
  final double lat;
  final double long;

  Stop(this.lat, this.long);
}

@freezed
abstract class UsableRoute with _$UsableRoute {
  const factory UsableRoute({
    required int idRoute,
    required bool isActiveRoute,
    required List<Stop> stops,
  }) = _UsableRoute;
}
