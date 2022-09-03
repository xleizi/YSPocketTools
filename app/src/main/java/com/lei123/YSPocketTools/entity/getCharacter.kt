package com.lei123.YSPocketTools.entity

class getCharacter {
    var retcode: Int? = null
    var message: String? = null
    var data: DataInfo? = null

    inner class DataInfo {
        var avatars: List<avatarsInfo>? = null
        var role: rolesInfo? = null

        inner class avatarsInfo {
            var id: String? = null
            var image: String? = null
            var icon: String? = null
            var name: String? = null
            var element: String? = null
            var fetter: String? = null
            var level: String? = null
            var rarity: String? = null
            var actived_constellation_num: String? = null
            var costumes: List<costumesInfos>? = null

            var weapon: WeaponInfo? = null
            var constellations: List<constellationsInfo>? = null
            var reliquaries: List<reliquariesInfo>? = null

            inner class costumesInfos {
                var id: Int? = null
                var name: String? = null
                var icon: String? = null
            }

            inner class WeaponInfo {
                var id: Int? = null
                var name: String? = null
                var icon: String? = null
                var type: Int? = null
                var rarity: Int? = null
                var level: Int? = null
                var promote_level: Int? = null
                var type_name: String? = null
                var desc: String? = null
                var affix_level: Int? = null
            }

            inner class constellationsInfo {
                var id: Int? = null
                var name: String? = null
                var icon: String? = null
                var effect: String? = null
                var is_actived: Boolean? = null
                var pos: Int? = null
            }

            inner class reliquariesInfo {
                var id: Int? = null
                var name: String? = null
                var icon: String? = null
                var pos: Int? = null
                var rarity: Int? = null
                var level: Int? = null
                var pos_name: String? = null
                var set: setInfo? = null

                inner class setInfo {
                    var id: Int? = null
                    var name: String? = null
                    var affixes: List<affixesInfo>? = null
                    inner class affixesInfo {
                        var activation_number: Int? = null
                        var effect: String? = null
                    }
                }
            }
        }

        inner class rolesInfo {
            var AvatarUrl: String? = null
            var nickname: String? = null
            var region: String? = null
            var level: String? = null
        }
    }
}