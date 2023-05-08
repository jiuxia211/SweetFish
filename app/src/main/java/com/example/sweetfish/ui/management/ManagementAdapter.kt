package com.example.sweetfish.ui.management

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.sweetfish.R


class ManagementAdapter :
    BaseExpandableListAdapter {
    var groups: MutableList<ManagementFirstList> = mutableListOf()
    var childs: MutableList<MutableList<Commodity>> =
        mutableListOf()
    lateinit var groupViewHolder: GroupViewHolder
    lateinit var childViewHolder: ChildViewHolder

    constructor(
        groups: MutableList<ManagementFirstList> = mutableListOf(),
        childs: MutableList<MutableList<Commodity>>
    ) : super() {
        this.groups = groups
        this.childs = childs
    }

    inner class GroupViewHolder(view: View) {
        var parentText: TextView? = null
        var parentImage: ImageView? = null

        init {
            parentText = view.findViewById(R.id.parent_text)
            parentImage = view.findViewById(R.id.parent_image)
        }
    }

    inner class ChildViewHolder(view: View) {
        var childCover: ImageView? = null
        var childTitle: TextView? = null
        var childAvatar: ImageView? = null
        var childUsername: TextView? = null
        var childPass: Button? = null
        var childFail: Button? = null

        init {
            childCover = view.findViewById(R.id.child_cover)
            childTitle = view.findViewById(R.id.child_title)
            childAvatar = view.findViewById(R.id.child_avatar)
            childUsername = view.findViewById(R.id.child_username)
            childPass = view.findViewById(R.id.child_pass)
            childFail = view.findViewById(R.id.child_fail)
        }
    }


    override

    fun getGroupCount(): Int {
        return groups.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return childs.get(groupPosition).size
    }

    override fun getGroup(groupPosition: Int): Any {
        return groups.get(groupPosition)
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return childs.get(groupPosition).get(childPosition)
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return (groupPosition + childPosition).toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var mconvertView = convertView
        if (convertView == null) {
            mconvertView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.management_parent_items, parent, false)
            groupViewHolder = GroupViewHolder(mconvertView)
            mconvertView?.tag = groupViewHolder
        } else {
            groupViewHolder = mconvertView?.tag as GroupViewHolder
        }
        groupViewHolder.parentText?.text =
            groups[groupPosition].parentText.toString()
        if (isExpanded) {
            groupViewHolder.parentImage?.setImageResource(R.drawable.eatwatermelon)
        } else {
            groupViewHolder.parentImage?.setImageResource(R.drawable.lovely)
        }
        return mconvertView!!
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var mconvertView = convertView
        if (convertView == null) {
            mconvertView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.management_child_items, parent, false)
            childViewHolder = ChildViewHolder(mconvertView)
            // 用mconvertView代替convertView，不然会有空指针异常
            mconvertView?.tag = childViewHolder
        } else {
            childViewHolder = mconvertView?.tag as ChildViewHolder
        }
        childViewHolder.childTitle?.text =
            childs[groupPosition][childPosition].title
        childViewHolder.childUsername?.text = childs[groupPosition][childPosition].username
        childViewHolder.childCover?.setImageResource(childs[groupPosition][childPosition].imageId)
        childViewHolder.childAvatar?.setImageResource(childs[groupPosition][childPosition].avatarId)

        return mconvertView!!
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }


}