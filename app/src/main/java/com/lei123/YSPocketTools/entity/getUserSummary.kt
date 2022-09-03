package com.lei123.YSPocketTools.entity

class getUserSummary {
    var retcode: Int? = null
    var message: String? = null
    var data: DataInfo? = null

    inner class DataInfo {
        var role: rolesInfo? = null
        var avatars: List<avatarsInfo>? = null
        var stats: statsInfo? = null
        var world_explorations: List<world_explorationsInfo>? = null
        var homes: List<homesInfo>? = null

        inner class rolesInfo {
            var AvatarUrl: String? = null
            var nickname: String? = null
            var region: String? = null
            var level: String? = null
        }

        inner class avatarsInfo {
            var id: String? = null
            var image: String? = null
            var name: String? = null
            var element: String? = null
            var fetter: String? = null
            var level: String? = null
            var rarity: String? = null
            var actived_constellation_num: String? = null
            var card_image: String? = null
            var is_chosen: Boolean? = false
        }

        inner class statsInfo {
            var active_day_number: Int? = null
            var achievement_number: Int? = null
            var anemoculus_number: Int? = null
            var geoculus_number: Int? = null
            var avatar_number: Int? = null
            var way_point_number: Int? = null
            var domain_number: Int? = null
            var spiral_abyss: String? = null
            var precious_chest_number: Int? = null
            var luxurious_chest_number: Int? = null
            var exquisite_chest_number: Int? = null
            var common_chest_number: Int? = null
            var electroculus_number: Int? = null
            var magic_chest_number: Int? = null
            var dendroculus_number: Int? = null
        }
        inner class world_explorationsInfo {
            var level: Int? = null
            var exploration_percentage: Int? = null
            var icon: String? = null
            var name: String? = null
            var type: String? = null
            var offerings: List<offeringsInfo>? = null
            inner class offeringsInfo {
                var name: String? = null
                var level: Int? = null
                var icon: String? = null
            }
            var id: Int? = null
            var parent_id: Int? = null
            var map_url: String? = null
            var strategy_url: String? = null
            var background_image: String? = null
            var inner_icon: String? = null
            var cover: String? = null
        }

        inner class homesInfo {
            var level: Int? = null
            var visit_num: Int? = null
            var comfort_num: Int? = null
            var item_num: Int? = null
            var name: String? = null
            var icon: String? = null
            var comfort_level_name: String? = null
            var comfort_level_icon: String? = null
        }

    }
}