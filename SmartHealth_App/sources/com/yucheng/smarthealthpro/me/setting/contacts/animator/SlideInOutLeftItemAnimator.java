package com.yucheng.smarthealthpro.me.setting.contacts.animator;

import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.yucheng.smarthealthpro.me.setting.contacts.animator.BaseItemAnimator;

/* loaded from: classes5.dex */
public class SlideInOutLeftItemAnimator extends BaseItemAnimator {
    public SlideInOutLeftItemAnimator(RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override // com.yucheng.smarthealthpro.me.setting.contacts.animator.BaseItemAnimator
    protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
        final ViewPropertyAnimatorCompat viewPropertyAnimatorCompatAnimate = ViewCompat.animate(holder.itemView);
        this.mRemoveAnimations.add(holder);
        viewPropertyAnimatorCompatAnimate.setDuration(getRemoveDuration()).alpha(0.0f).translationX(-this.mRecyclerView.getLayoutManager().getWidth()).setListener(new BaseItemAnimator.VpaListenerAdapter() { // from class: com.yucheng.smarthealthpro.me.setting.contacts.animator.SlideInOutLeftItemAnimator.1
            @Override // com.yucheng.smarthealthpro.me.setting.contacts.animator.BaseItemAnimator.VpaListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public void onAnimationStart(View view) {
                SlideInOutLeftItemAnimator.this.dispatchRemoveStarting(holder);
            }

            @Override // com.yucheng.smarthealthpro.me.setting.contacts.animator.BaseItemAnimator.VpaListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public void onAnimationEnd(View view) {
                viewPropertyAnimatorCompatAnimate.setListener(null);
                ViewCompat.setAlpha(view, 1.0f);
                ViewCompat.setTranslationX(view, -SlideInOutLeftItemAnimator.this.mRecyclerView.getLayoutManager().getWidth());
                SlideInOutLeftItemAnimator.this.dispatchRemoveFinished(holder);
                SlideInOutLeftItemAnimator.this.mRemoveAnimations.remove(holder);
                SlideInOutLeftItemAnimator.this.dispatchFinishedWhenDone();
            }
        }).start();
    }

    @Override // com.yucheng.smarthealthpro.me.setting.contacts.animator.BaseItemAnimator
    protected void prepareAnimateAdd(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, -this.mRecyclerView.getLayoutManager().getWidth());
    }

    @Override // com.yucheng.smarthealthpro.me.setting.contacts.animator.BaseItemAnimator
    protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
        final ViewPropertyAnimatorCompat viewPropertyAnimatorCompatAnimate = ViewCompat.animate(holder.itemView);
        this.mAddAnimations.add(holder);
        viewPropertyAnimatorCompatAnimate.translationX(0.0f).alpha(1.0f).setDuration(getAddDuration()).setListener(new BaseItemAnimator.VpaListenerAdapter() { // from class: com.yucheng.smarthealthpro.me.setting.contacts.animator.SlideInOutLeftItemAnimator.2
            @Override // com.yucheng.smarthealthpro.me.setting.contacts.animator.BaseItemAnimator.VpaListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public void onAnimationStart(View view) {
                SlideInOutLeftItemAnimator.this.dispatchAddStarting(holder);
            }

            @Override // com.yucheng.smarthealthpro.me.setting.contacts.animator.BaseItemAnimator.VpaListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public void onAnimationCancel(View view) {
                ViewCompat.setTranslationX(view, 0.0f);
                ViewCompat.setAlpha(view, 1.0f);
            }

            @Override // com.yucheng.smarthealthpro.me.setting.contacts.animator.BaseItemAnimator.VpaListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public void onAnimationEnd(View view) {
                viewPropertyAnimatorCompatAnimate.setListener(null);
                ViewCompat.setTranslationX(view, 0.0f);
                ViewCompat.setAlpha(view, 1.0f);
                SlideInOutLeftItemAnimator.this.dispatchAddFinished(holder);
                SlideInOutLeftItemAnimator.this.mAddAnimations.remove(holder);
                SlideInOutLeftItemAnimator.this.dispatchFinishedWhenDone();
            }
        }).start();
    }
}
