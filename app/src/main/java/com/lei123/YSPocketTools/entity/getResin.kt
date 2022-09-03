package com.lei123.YSPocketTools.entity

class getResin {
    var retcode: Int? = null
    var message: String? = null
    var data: DataInfo? = null

    inner class DataInfo {
        var current_resin: String? = null
        var max_resin: String? = null
        var resin_recovery_time: String? = null
        var finished_task_num: String? = null
        var total_task_num: String? = null
        var is_extra_task_reward_received: Boolean? = null
        var remain_resin_discount_num: String? = null
        var resin_discount_num_limit: String? = null
        var current_expedition_num: String? = null
        var max_expedition_num: String? = null
        var current_home_coin: String? = null
        var max_home_coin: String? = null
        var home_coin_recovery_time: String? = null
        var calendar_url: String? = null
        var expeditions: List<expeditionsInfo>? = null
        var transformer: transformerInfo? = null

        inner class expeditionsInfo {
            var avatar_side_icon: String? = null
            var status: String? = null
            var remained_time: String? = null
        }

        inner class transformerInfo {
            var obtained: Boolean? = null
            var wiki: String? = null
            var noticed: Boolean? = null
            var latest_job_id: String? = null
            var recovery_time: transreInfo? = null

            inner class transreInfo {
                var Day: String? = "0"
                var Hour: String? = "0"
                var Minute: String? = "0"
                var Second: String? = "0"
                var reached: Boolean? = null
            }
        }
    }
}